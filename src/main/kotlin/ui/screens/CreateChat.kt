package ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.push
import controller.ChatController
import controller.UserController
import db.GetFriends
import ui.components.CustomButton
import ui.components.NavBarStandard
import ui.components.ScreenLayout
import ui.navigation.Configuration

@Composable
fun CreateChat(router: Router<Configuration, Any>) {
    val selectedMap = remember { mutableStateMapOf<String, Boolean>(UserController.username!! to true) }

    Column {
        ScreenLayout(modifier = Modifier.weight(1f)) {
            NavBarStandard(title = { Text("Create Chat") })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                for (friend in UserController.friends) {
                    key(friend.username) {
                        val isSelected = selectedMap.getOrDefault(friend.username, false)
                        SelectFriend(
                            friend,
                            onClick = {selectedMap[friend.username] = !isSelected},
                            isSelected = isSelected,
                        )
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            CustomButton(
                onClick = {
                    val chat = ChatController.createChat(selectedMap.entries
                        .filter { (username, selected) -> selected }
                        .map{ (username, selected) -> username })
                    router.push(Configuration.ChatRoom(chat))
                },
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text("Create")
            }
        }
    }
}

@Composable
fun SelectFriend(friend: GetFriends, onClick: () -> Unit, isSelected: Boolean) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(friend.display_name)
        Checkbox(
            checked = isSelected,
            onCheckedChange = {check -> onClick()}
        )
    }
}