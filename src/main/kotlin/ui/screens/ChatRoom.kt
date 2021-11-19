package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.ChatController
import controller.UserController
import entity.Chat
import entity.Message
import repository.utils.makeQueryState
import ui.components.CustomButton
import ui.components.CustomTextField
import ui.components.NavBarStandard
import ui.components.ScreenLayout
import ui.theme.InkLight
import ui.theme.PrimaryBase

@Composable
fun ChatRoom(chat_id: Number) {
    val chatState = makeQueryState { ChatController.getChat(chat_id) ?: Chat() }
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
                for (message in chat.messages) {
                    Spacer(Modifier.size(8.dp))
                    key(message.id) { TextMessage(message) }
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
            Spacer(Modifier.width(8.dp))
            CustomButton(
                onClick = {
                    ChatController.sendMessage(chat.id, value)
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
fun TextMessage(message: Message) {
    Column () {
        Text (message.sender, color = InkLight)
        Spacer(Modifier.size(4.dp))
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(PrimaryBase)
                .padding(8.dp)
        ) {
            Text(text = message.text, color = Color.White)
        }
    }
}