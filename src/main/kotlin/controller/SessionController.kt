package controller

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import entity.Session
import repository.SessionRepository

object SessionController {

    val mySessions by derivedStateOf {
//        SessionRepository.sessions.filter { it.creatorId == UserController.username }
//        SessionRepository.fetchMySession("John")
        SessionRepository.mySessions
    }
    val myRequests by derivedStateOf {
        SessionRepository.sessions
            .filter { it.expertId == UserController.username }
            .filter { it.status == Session.Status.PENDING }
    }
}
