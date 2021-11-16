package ui.screens

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import controller.SessionController
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.theme.*

@Composable
fun MySessions(
    onNewSessionClick: () -> Unit
) {
    val showPopup = remember { mutableStateOf(false) }
    val sessionTitle = remember { mutableStateOf("")}
    ScreenLayout {

        Box()
        {
            Column() {
                Box(
                ) {
                    NavBarLarge(
                        title = { Text("My Sessions") },
                        actionButtons = {
                            Button(
                                onClick = onNewSessionClick,
                                shape = CircleShape
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
                                sessionTitle.value = it
                            }, session)
                        }
                    }
                }
            }
            val bgColor by animateColorAsState(if (showPopup.value) Color.Black.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.0f))
            if (showPopup.value) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(bgColor)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        SessionPopUp(title = sessionTitle.value,onClose = { showPopup.value = !showPopup.value })
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
fun SessionRow(onOpen: (title: String) -> Unit, session: entity.Session) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 21.dp)
            .padding(start = 24.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
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
            when (session.status){
                "PENDING" -> {
                    statusText = "Expert is pending"
                    statusColor = SkyDark
                    statusColorBg = SkyLightest
                    statusPath = "icons/pending.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = SkyDark
                }
                "ACCEPTED" -> {
                    statusText = "Expert has accepted"
                    statusColor = GreenDarkest
                    statusColorBg = GreenLightest
                    statusPath = "icons/accepted.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = GreenDarkest
                }
                "ENDED" -> {
                    statusText = "Ended"
                    statusColor = PrimaryDark
                    statusColorBg = PrimaryLightest
                    statusPath = "icons/ended.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = PrimaryDark
                }
                "REVIEWED" -> {
                    statusText = "Reviewed"
                    statusColor = PrimaryDark
                    statusColorBg = PrimaryLightest
                    statusPath = "icons/ended.svg"
                    statusMod = Modifier.padding(start = 3.dp, end = 10.dp).padding(top = 1.dp)
                    statusTint = PrimaryDark
                }
                "CANCELED" -> {
                    statusText = "Cancelled"
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
        IconButton( onClick = { onOpen(session.topic) }) {
            Icon(Icons.Default.MoreVert, "")
        }

    }
}

@Composable
fun SessionPopUp(title: String ,onClose: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
        ,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text= title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Title3,
            color = InkDarkest,
            modifier = Modifier
                .padding(bottom = 16.dp)

        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .clickable {  }
                .padding(all = 10.dp)

        ) {
            Icon(painterResource("icons/delete.svg"), "",modifier = Modifier
                .padding(horizontal = 10.dp)
            )
            Text(
                text = "Cancel session",
                style = SmallNormalRegular,
                color = InkDarkest,
                modifier = Modifier
                    .padding(top = 3.dp)
//                    .padding(bottom = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
        ){

        }
        Divider(
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = onClose ,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
            ,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ){
            Text(
                "Cancel",
                color = PrimaryDark
                )
        }
    }

}

