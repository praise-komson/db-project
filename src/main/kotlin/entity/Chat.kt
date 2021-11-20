package entity

import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant
import java.time.format.DateTimeFormatter

data class Chat (
    val _id: Id<Chat> = newId(),
    val messages: List<Message> = emptyList(),
    val members: List<String> = emptyList(),
) {
    constructor(members: List<String>) : this (
        _id = newId(),
        messages = emptyList(),
        members = members,
    )
}

data class Message (
    val text: String,
    val sender: String,
    val timestamp: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
//    val attachments: Array<File>
)