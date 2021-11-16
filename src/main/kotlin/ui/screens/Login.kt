package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controller.UserController
import db.User
import repository.UserRepository
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.theme.LargeNoneMedium
import ui.theme.PrimaryDark
import ui.theme.PrimaryLightest
import ui.theme.SmallNoneRegular

@Composable
fun Login() {
    ScreenLayout {
        NavBarLarge(
            title = { Text(text = "Login") }
        )
        LazyColumn {
            itemsIndexed(UserRepository.users) { index, user ->
                if (index > 0) {
                    Divider()
                }
                UserRow(user)
            }
        }
    }
}

@Composable
fun UserRow(user: User) {
    val isExpert = UserRepository.experts.any { it.username == user.username }
    Row(
        modifier = Modifier
            .clickable {
                UserController.username = user.username
            }
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = user.display_name,
            style = LargeNoneMedium
        )
        if (isExpert) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PrimaryLightest)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ){
                Text(
                    text = "Expert",
                    style = SmallNoneRegular,
                    color = PrimaryDark
                )
            }
        }
    }
}
