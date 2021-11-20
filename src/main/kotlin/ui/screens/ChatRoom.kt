package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.ChatController
import controller.UserController
import entity.Chat
import entity.Message
import org.litote.kmongo.Id
import repository.utils.makeQueryState
import ui.components.CustomButton
import ui.components.CustomTextField
import ui.components.NavBarStandard
import ui.components.ScreenLayout
import ui.theme.*

@Composable
fun ChatRoom(chat_id: Id<Chat>) {
    val chatState = makeQueryState { ChatController.getChat(chat_id) }
    val chat by chatState

    Column {
        ScreenLayout (modifier = Modifier.weight(1f)) {
            NavBarStandard(title = {
                Text(chat.members
                    .filter { member -> member != UserController.username }
                    .joinToString(", "))
            })
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                for ((index, message) in chat.messages.withIndex()) {
                    key (index) {
                        Spacer(Modifier.size(8.dp))
                        TextMessage(
                            message,
                            if (index > 0) {
                                chat.messages[index].sender != chat.messages[index-1].sender
                            }
                            else true
                        )
                    }
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 24.dp),
        ) {
            val (value, setValue) = remember { mutableStateOf("") }
            CustomTextField(
                value = value,
                onValueChange = setValue,
                label = { Text("Chat") },
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(16.dp))
            CustomButton(
                onClick = {
                    ChatController.sendMessage(chat._id, value)
                    setValue("")
                    chatState.refetch()
                }
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun TextMessage(message: Message, showName: Boolean) {
    val isSender = message.sender == UserController.username
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isSender) Alignment.End else Alignment.Start
    ) {
        if (showName) {
            Text (message.sender, color = InkLight)
            Spacer(Modifier.size(8.dp))
        }
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(if (isSender) PrimaryBase else SkyLight)
                .padding(12.dp)
        ) {
            Text(text = message.text, color = if (isSender) Color.White else InkDarkest)
        }
    }
}