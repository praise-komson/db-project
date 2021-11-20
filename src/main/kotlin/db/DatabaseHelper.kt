package db

import com.mongodb.client.MongoDatabase
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.sqlite.driver.JdbcDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.litote.kmongo.KMongo
import repository.UserRepository

object DatabaseHelper {

    val driver: JdbcDriver
    val database: Database
    val mongoDb: MongoDatabase

    init {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://localhost:3307/doji"
        config.username = "root"
        config.password = "doji"
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        val ds = HikariDataSource(config)
        driver = ds.asJdbcDriver()
        database = Database(
            driver,
            sessionAdapter = Session.Adapter(
                statusAdapter = EnumColumnAdapter()
            )
        )
        migrate()
        createStoredProcedure()

        mongoDb = KMongo.createClient("mongodb://localhost:27018").getDatabase("doji")
    }

    private fun migrate() {
        Database.Schema.migrate(driver, 1, 2)
        val settings = database.settingsQueries.getSettings().executeAsOne()
        val dbVersion = settings.version
        val schemaVersion = Database.Schema.version
        println("Current db version: $dbVersion")
        for (version in (dbVersion until schemaVersion)) {
            println("Migrating to ${version + 1}")
            Database.Schema.migrate(driver, version, version + 1)
            database.settingsQueries.setVersion(version + 1)
        }
    }

    private fun createStoredProcedure() {
        driver.execute(0, "DROP PROCEDURE IF EXISTS ConductCoinTransaction;", 0)
        driver.execute(
            1,
            """
CREATE PROCEDURE ConductCoinTransaction (
    IN SessionId INT(4),
    IN Username VARCHAR(255),
    IN Amount BIGINT(8),
    IN Description TEXT(500)
) BEGIN

UPDATE
    user u
SET
    u.coin_balance = u.coin_balance + Amount
WHERE
    u.username = Username;

UPDATE session SET coin_on_hold = coin_on_hold - Amount WHERE id = SessionId;

INSERT INTO
    doji_coin_transaction (
        description,
        amount,
        timestamp,
        external_transaction_id,
        source_id,
        user_id
    )
VALUES
    (
        Description,
        Amount,
        NOW(),
        0,
        (
            SELECT
                source_id
            FROM
                session s
            WHERE
                s.id = SessionId
        ),
        Username
    );
END;
            """,
            0
        )
    }

    fun conductCoinTransaction(session_id: Int, username: String, amount: Long, description: String) {
        driver.execute(
            2,
            "CALL ConductCoinTransaction(?,?,?,?)",
            4
        ) {
            bindLong(1, session_id.toLong())
            bindString(2, username)
            bindLong(3, amount)
            bindString(4, description)
        }
        UserRepository.refetchUsers()
    }
}
