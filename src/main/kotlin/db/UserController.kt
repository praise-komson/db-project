package db

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserController {

    private val userQueries = DatabaseHelper.database.userQueries
    private val users = userQueries.getUsers().executeAsList()
    private val experts = userQueries.getExperts().executeAsList()

    private var username by mutableStateOf<String?>("John")

    val user by derivedStateOf { users.firstOrNull { it.username == username } }
    val isLoggedIn by derivedStateOf { user != null }
    val isExpert by derivedStateOf { experts.any { it.username == username } }
}
