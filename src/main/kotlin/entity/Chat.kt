package entity

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Chat (
    val _id: Id<Chat> = newId(),
    val messages: List<Message> = emptyList(),
    val members: List<String> = emptyList(),
)

data class Message (
    val timestamp: String,
    val text: String,
    val sender: String,
//    val attachments: Array<File>
)