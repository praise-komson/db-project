package repository

import androidx.compose.runtime.getValue
import controller.UserController
import db.DatabaseHelper
import entity.Session
import repository.utils.makeQueryState

object SessionRepository {

    private val sessionQueries = DatabaseHelper.database.sessionQueries

    private val sessionsState = makeQueryState { sessionQueries.getSessions().executeAsList().map(::Session) }
    val sessions by sessionsState

    private val mySessionsState = makeQueryState {
        UserController.username?.let { sessionQueries.getMySession(user_id = it).executeAsList().map(::Session) } ?: emptyList()
    }
    val mySessions by mySessionsState

    fun insertSession(session: Session): Session {
        val sessionId = sessionQueries.transactionWithResult<Long> {
            sessionQueries.insertSession(
                session.meetingProviderId,
                session.fee,
                session.coinOnHold,
                session.status,
                session.topic,
                session.duration,
                session.startTime,
                session.sourceId,
                session.creatorId,
                session.expertId,
                session.serviceName,
            )
            return@transactionWithResult sessionQueries.lastInsertRowId().executeAsOne()
        }
        sessionsState.refetch()
        mySessionsState.refetch()
        session.id = sessionId.toInt()
        return session
    }

    fun insertSessionParticipant(session_id: Int, user_id: String) {
        sessionQueries.insertSessionParticipant(session_id, user_id)
    }

    fun updateSession(session: Session) {
        sessionQueries.updateSession(
            id = session.id,
            coin_on_hold = session.coinOnHold,
            status = session.status
        )
        sessionsState.refetch()
    }

    fun cancelSession(session: Session) {
        sessionQueries.cancelSession(
            id = session.id
        )
        mySessionsState.refetch()
    }
}
