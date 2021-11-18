package repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import controller.UserController
import db.DatabaseHelper
import entity.Session

object SessionRepository {

    private val sessionQueries = DatabaseHelper.database.sessionQueries
    var sessions by mutableStateOf(emptyList<Session>())
        private set
    var mySessions by mutableStateOf(emptyList<Session>())
        private set

    init {
        fetchSessions()
        fetchMySession()
    }

    fun fetchSessions() {
        sessions = sessionQueries.getSessions().executeAsList().map(::Session)
    }

    fun fetchMySession(){
        mySessions = UserController.username?.let { sessionQueries.getMySession(user_id = it).executeAsList().map(::Session) }!!
    }

    fun updateSession(session: Session) {
        sessionQueries.updateSession(
            id = session.id,
            coin_on_hold = session.coinOnHold,
            status = session.status
        )
        fetchSessions()
    }
    fun cancelSession(session: Session){
        sessionQueries.cancelSession(
            id = session.id
        )
        fetchMySession()
    }
}
