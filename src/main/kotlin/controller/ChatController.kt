package controller

import androidx.compose.runtime.getValue
import com.mongodb.client.FindIterable
import db.DatabaseHelper
import entity.Chat
import entity.Message
import org.litote.kmongo.*
import repository.utils.makeQueryState
import java.time.Instant
import java.time.format.DateTimeFormatter

object ChatController {
    private val chats = DatabaseHelper.mongoDb.getCollection<Chat>("Chat")
    private val myChatsState = makeQueryState {
        chats.find(Chat::members contains UserController.username)
    }
    val myChats: FindIterable<Chat> by myChatsState
    fun sendMessage(chat_id: Number, text: String) {
        val message = Message(123, DateTimeFormatter.ISO_INSTANT.format(Instant.now()), text, UserController.username!!)
        chats.updateOne(Chat::id eq chat_id, push(
            Chat::messages, message
        ))
    }
    fun getChat(chat_id: Number): Chat? {
        return chats.findOne(Chat::id eq chat_id)
    }
}