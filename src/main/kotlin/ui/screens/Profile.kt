package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.UserController
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.theme.LargeNoneMedium
import ui.theme.RegularNoneRegular

@Composable
fun Profile(onRouteChat: () -> Unit) {
    ScreenLayout {
        NavBarLarge(title = { Text(text = "Profile") })
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            Text(
                text = UserController.user?.display_name?: "",
                style = LargeNoneMedium
            )
            Text(
                text = UserController.user?.coin_balance.toString() + " coins",
                style = RegularNoneRegular
            )
            Button(onClick = { UserController.username = null }) {
                Text(text = "Logout")
            }
            Button(onClick = onRouteChat) {
                Text(text = "My Chats")
            }
        }
    }
}
