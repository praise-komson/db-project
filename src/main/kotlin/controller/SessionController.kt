package controller

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import repository.SessionRepository

object SessionController {

    val mySessions by derivedStateOf {
        SessionRepository.sessions.filter { it.creatorId == UserController.username }
    }
    val myRequests by derivedStateOf {
        SessionRepository.sessions
            .filter { it.expertId == UserController.username }
            .filter { it.status == "PENDING" }
    }
}
