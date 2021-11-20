package ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import controller.UserController
import repository.UserRepository
import ui.components.*
import ui.theme.*
import ui.util.imagePainter

@Composable
fun Profile(onRouteChat: () -> Unit) {
    val user = UserController.user ?: return
    var showTopUpDialog by remember { mutableStateOf(false) }
    TopUpDialog(
        showTopUpDialog,
        onDismissRequest = { showTopUpDialog = false }
    ) { amount ->
        UserRepository.topUpUser(user.username, amount)
    }

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
            RowButton(onClick = { showTopUpDialog = true }) {
                Image(
                    painter = painterResource("icons/credit-card.svg"),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                )
                Text(text = "Top up")
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopUpDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onTopUp: (amount: Int) -> Unit
) {
    Popup {
        Column {
            AnimatedVisibility(
                visible = show,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PopupScrim(onDismissRequest)
            }
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = show,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                        .background(SkyWhite)
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Top up",
                        color = InkDarkest,
                        style = Title3
                    )
                    TopUpButton(100, onTopUp)
                    TopUpButton(500, onTopUp)
                    TopUpButton(1000, onTopUp)
                    CustomButton(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = buttonColorsTransparent()
                    ) {
                        Text("Done")
                    }
                }
            }
        }
    }
}

@Composable
fun TopUpButton(
    amount: Int,
    onTopUp: (amount: Int) -> Unit
) {
    CustomButton(
        onClick = { onTopUp(amount) },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(48.dp),
        colors = if (amount == 100) buttonColorsPrimary() else buttonColorsSecondary()
    ) {
        Text("Add $amount")
    }
}
