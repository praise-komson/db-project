package ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import db.GetMySessions
import entity.Session
import repository.SessionRepository
import ui.components.CustomButton
import ui.components.NavBarLarge
import ui.components.PopupScrim
import ui.components.ScreenLayout
import ui.theme.*
import ui.util.imagePainter

@Composable
fun MySessions(
    onNewSessionClick: () -> Unit
) {
    var showPopup by remember { mutableStateOf(false) }
    var popUpSession by remember { mutableStateOf<GetMySessions?>(null) }

    ScreenLayout {
        Column {
            Box {
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
                    itemsIndexed(SessionRepository.mySessions) { index, session ->
                        if (index > 0) {
                            Divider()
                        }
                        SessionRow(onOpen = {
                            showPopup = true
                            popUpSession = session
                        }, session)
                    }
                }
            }
        }
    }
    Popup {
        Column {
            AnimatedVisibility(
                visible = showPopup,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PopupScrim { showPopup = false }
            }
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = showPopup,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                        .background(SkyWhite)
                ) {
                    SessionPopUp(session = popUpSession!!, onClose = { showPopup = false })
                }
            }
        }
    }
}

@Composable
fun SessionRow(onOpen: () -> Unit, session: GetMySessions) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 21.dp)
            .padding(start = 24.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = imagePainter(session.expert_profile_pic_url),
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
                text = session.start_time,
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
                Row {
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
        IconButton( onClick = { onOpen() }) {
            Icon(Icons.Default.MoreVert, "")
        }
    }
}

@Composable
fun SessionPopUp(session: GetMySessions ,onClose: () -> Unit){
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
                    SessionRepository.cancelSession(session.id)
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
