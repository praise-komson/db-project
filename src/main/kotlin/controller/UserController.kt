package controller

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import repository.UserRepository

object UserController {

    var username by mutableStateOf<String?>("John")

    val user by derivedStateOf { UserRepository.users.firstOrNull { it.username == username } }
    val isLoggedIn by derivedStateOf { user != null }
    val isExpert by derivedStateOf { UserRepository.experts.any { it.username == username } }
}
