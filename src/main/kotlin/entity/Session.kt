package entity

import repository.SessionRepository
import repository.UserRepository
import db.Session as DbSession

data class Session(
    val id: Int,
    val meetingProviderId: String,
    val fee: Long,
    var coinOnHold: Long,
    var status: String,
    val topic: String,
    val duration: Int,
    val startTime: String,
    val sourceId: Int,
    val creatorId: String,
    val expertId: String,
    val serviceName: String
) {
    constructor(dbSession: DbSession) : this(
        id = dbSession.id,
        meetingProviderId = dbSession.meeting_provider_id,
        fee = dbSession.fee,
        coinOnHold = dbSession.coin_on_hold,
        status = dbSession.status,
        topic = dbSession.topic,
        duration = dbSession.duration,
        startTime = dbSession.start_time,
        sourceId = dbSession.source_id,
        creatorId = dbSession.creator_id,
        expertId = dbSession.expert_id,
        serviceName = dbSession.service_name
    )
    constructor() : this(
        id = 1,
        meetingProviderId = "1",
        fee = 1,
        coinOnHold = 1,
        status = "1",
        topic = "1",
        duration = 1,
        startTime = "1",
        sourceId = 1,
        creatorId = "1",
        expertId = "1",
        serviceName = "1"
    )

    val expert by lazy { UserRepository.experts.first { it.username == expertId } }

    fun save() {
        SessionRepository.updateSession(this)
    }
    fun cancel(){
        SessionRepository.cancelSession(this)
    }
}
