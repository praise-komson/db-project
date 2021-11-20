package repository

import androidx.compose.runtime.getValue
import controller.UserController
import db.DatabaseHelper
import entity.Session
import repository.utils.makeQueryState

object SessionRepository {

    private val sessionQueries = DatabaseHelper.database.sessionQueries

    private val mySessionsState = makeQueryState {
        UserController.username?.let { sessionQueries.getMySessions(user_id = it).executeAsList() } ?: emptyList()
    }
    val mySessions by mySessionsState

    private val myRequestsState = makeQueryState {
        UserController.username?.let { sessionQueries.getMyRequests(user_id = it).executeAsList() } ?: emptyList()
    }
    val myRequests by myRequestsState

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
        mySessionsState.refetch()
        myRequestsState.refetch()
        session.id = sessionId.toInt()
        return session
    }

    fun insertSessionParticipant(session_id: Int, user_id: String) {
        sessionQueries.insertSessionParticipant(session_id, user_id)
    }

    fun updateSessionStatus(sessionId: Int, status: Session.Status) {
        sessionQueries.updateSessionStatus(
            id = sessionId,
            status = status
        )
        mySessionsState.refetch()
        myRequestsState.refetch()
    }

    fun cancelSession(sessionId: Int) {
        val (refundAmount, creatorId) = sessionQueries.getRefundInfo(id = sessionId).executeAsOne()
        DatabaseHelper.conductCoinTransaction(sessionId, creatorId, refundAmount, "refund")
        sessionQueries.cancelSession(
            id = sessionId
        )
        mySessionsState.refetch()
        myRequestsState.refetch()
    }
}
