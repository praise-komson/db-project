package repository

import db.DatabaseHelper

object UserRepository {

    private val userQueries = DatabaseHelper.database.userQueries
    val users = userQueries.getUsers().executeAsList()
    val experts = userQueries.getExperts().executeAsList()
}
