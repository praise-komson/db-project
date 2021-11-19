package ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import controller.ChatController
import ui.components.ScreenLayout
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.push
import controller.UserController
import ui.components.NavBarStandard
import ui.navigation.Configuration

@Composable
fun MyChats(router: Router<Configuration, Any>) {
    ScreenLayout {
        NavBarStandard(title = { Text(text = "Chats") })
        for (chat in ChatController.myChats) {
            Divider ()
            Row(
                modifier = Modifier
                    .clickable {
                        val newConfig = Configuration.ChatRoom(chat)
                        router.push(newConfig)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(chat.members
                    .filter{ member -> member != UserController.username}
                    .joinToString(", ")
                )
            }
        }
    }
}

