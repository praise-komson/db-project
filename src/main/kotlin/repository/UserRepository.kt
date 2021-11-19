package repository

import androidx.compose.runtime.getValue
import db.DatabaseHelper
import repository.utils.makeQueryState

object UserRepository {

    private val userQueries = DatabaseHelper.database.userQueries

    private val usersState = makeQueryState { userQueries.getUsers().executeAsList() }
    val users by usersState
    val experts = userQueries.getExperts().executeAsList()

    fun refetchUsers() {
        usersState.refetch()
    }
}
