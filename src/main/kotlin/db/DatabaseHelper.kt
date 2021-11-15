package db

import com.squareup.sqldelight.sqlite.driver.JdbcDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DatabaseHelper {

    val driver: JdbcDriver
    val database: Database

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
        database = Database(driver)
        migrate()
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
}
