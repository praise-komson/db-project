package entity

import db.Service as DbService

data class Service(
    val expertId: String,
    val sname: String,
    val fee: Long,
    val description: String
) {
    constructor() : this(
        expertId = "1",
        sname = "1",
        fee = 1,
        description = "1"
    )

    constructor(dbService: DbService) : this(
        expertId = dbService.expert_id,
        sname = dbService.sname,
        fee = dbService.fee,
        description = dbService.description
    )
}