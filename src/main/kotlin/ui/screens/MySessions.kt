package ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import controller.SessionController
import entity.Session
import repository.SessionRepository
import ui.components.CustomButton
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.theme.*
import ui.util.imagePainter

@Composable
fun MySessions(
    onNewSessionClick: () -> Unit
) {
    val showPopup = remember { mutableStateOf(false) }
    var popUpSession = Session()
    ScreenLayout {

        Box()
        {
            Column() {
                Box(
                ) {
                    NavBarLarge(
                        title = { Text("My Sessions") },
                        actionButtons = {
                            CustomButton(
                                onClick = onNewSessionClick
                            ) {
                                Text("New")
                                Icon(Icons.Default.Add, "")
                            }
                        }
                    )
                }
                Box {
                    LazyColumn {
                        itemsIndexed(SessionController.mySessions) { index, session ->
                            if (index > 0) {
                                Divider()
                            }
                            SessionRow(onOpen = { showPopup.value = !showPopup.value
                                popUpSession = it
                            }, session)
                        }
                    }
                }
            }
            val bgColor by animateColorAsState(if (showPopup.value) InkDarkest.copy(alpha = 0.7f) else InkDarkest.copy(alpha = 0.0f))
            if (showPopup.value) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(bgColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd)
                            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                            .background(SkyWhite)
                    ) {
                        SessionPopUp(session = popUpSession, onClose = { showPopup.value = !showPopup.value })
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        showPopup.value = !showPopup.value
                    }
                )
            }
        }

    }
}

@Composable
fun SessionRow(onOpen: (Session) -> Unit, session: Session) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 21.dp)
            .padding(start = 24.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = imagePainter(session.expert.profile_pic_url),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = session.topic,
                color = InkDarkest,
                style = RegularTightMedium
            )
            Text(
                text = session.startTime,
                color = InkLighter,
                style = SmallTightRegular
            )
            var statusText = ""
            var statusColor = Color.Red
            var statusColorBg = Color.Red
            var statusPath = ""
            var statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 4.dp)
            var statusTint = Color.Red
            when (session.status) {
                Session.Status.PENDING -> {
                    statusText = "Expert is pending"
                    statusColor = SkyDark
                    statusColorBg = SkyLightest
                    statusPath = "icons/pending.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = SkyDark
                }
                Session.Status.ACCEPTED -> {
                    statusText = "Expert has accepted"
                    statusColor = GreenDarkest
                    statusColorBg = GreenLightest
                    statusPath = "icons/accepted.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = GreenDarkest
                }
                Session.Status.ENDED -> {
                    statusText = "Ended"
                    statusColor = PrimaryDark
                    statusColorBg = PrimaryLightest
                    statusPath = "icons/ended.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = PrimaryDark
                }
                Session.Status.REVIEWED -> {
                    statusText = "Reviewed"
                    statusColor = PrimaryDark
                    statusColorBg = PrimaryLightest
                    statusPath = "icons/ended.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = PrimaryDark
                }
                Session.Status.DECLINED -> {
                    statusText = "Expert has declined"
                    statusColor = RedDarkest
                    statusColorBg = RedLightest
                    statusPath = "icons/cancel.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 4.dp)
                    statusTint = RedDarkest
                }
                Session.Status.CANCELED -> {
                    statusText = "Canceled"
                    statusColor = RedDarkest
                    statusColorBg = RedLightest
                    statusPath = "icons/cancel.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 4.dp)
                    statusTint = RedDarkest
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(statusColorBg)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ){
                Row(
//                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(painterResource(statusPath), "", modifier = statusMod,
                        tint = statusTint
                    )
                    Text(
                        text = statusText,
                        style = SmallNoneRegular,
                        color = statusColor
                    )
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        IconButton( onClick = { onOpen(session) }) {
            Icon(Icons.Default.MoreVert, "")
        }

    }
}

@Composable
fun SessionPopUp(session: Session ,onClose: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text= session.topic,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Title3,
            color = InkDarkest,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    SessionRepository.cancelSession(session)
                    onClose()
                }
                .height(56.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource("icons/delete.svg"), "",modifier = Modifier
                .padding(end = 8.dp)
            )
            Text(
                text = "Delete session",
                style = SmallNormalRegular,
                color = InkDarkest
            )
        }
        Divider(
            color = SkyLighter,
            modifier = Modifier
                .padding(top = 24.dp)
        )
        TextButton(
            onClick = onClose,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                "Cancel",
                color = PrimaryDark
            )
        }
    }
}
