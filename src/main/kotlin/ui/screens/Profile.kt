package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import controller.UserController
import ui.components.ScreenLayout
import ui.theme.*
import ui.util.imagePainter

@Composable
fun Profile(onRouteChat: () -> Unit) {
    val user = UserController.user ?: return
    ScreenLayout {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Image(
                    painter = imagePainter(user.profile_pic_url),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp).height(64.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = user.display_name,
                        color = InkDarkest,
                        style = Title3
                    )
                    Text(
                        text = "${user.coin_balance} coins",
                        color = InkDarkest,
                        style = RegularNormalRegular
                    )
                }
            }
            RowButton(onClick = onRouteChat) {
                Image(
                    painter = painterResource("icons/message.svg"),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                )
                Text(text = "Chats")
            }
            Divider(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                color = SkyLighter
            )
            RowButton(onClick = { UserController.username = null }) {
                Text(text = "Logout")
            }
        }
    }
}

@Composable
fun RowButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides RegularTightRegular,
            LocalContentColor provides InkDarkest
        ) {
            content()
        }
    }
}
