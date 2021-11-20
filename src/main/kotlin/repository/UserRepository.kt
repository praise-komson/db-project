package repository

import androidx.compose.runtime.getValue
import db.DatabaseHelper
import db.GetFriends
import repository.utils.makeQueryState

object UserRepository {

    private val userQueries = DatabaseHelper.database.userQueries

    private val usersState = makeQueryState { userQueries.getUsers().executeAsList() }
    val users by usersState
    val experts = userQueries.getExperts().executeAsList()

    fun refetchUsers() {
        usersState.refetch()
    }

    fun topUpUser(username: String, amount: Int) {
        userQueries.topUpUser(username = username, amount = amount.toLong(), description = "top-up")
        refetchUsers()
    }

    fun getFriends(username: String): List<GetFriends> {
        return userQueries.getFriends(username = username).executeAsList()
    }
}
