package ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ui.components.NavBarLarge
import ui.components.ScreenLayout

@Composable
fun SessionRequests() {
    ScreenLayout {
        NavBarLarge(
            title = { Text(text = "Session Requests") }
        )
    }
}
