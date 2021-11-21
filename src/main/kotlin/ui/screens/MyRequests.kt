package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import db.GetMyRequests
import entity.Session
import repository.SessionRepository
import ui.components.CustomButton
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.components.buttonColorsSecondary
import ui.theme.InkDarkest
import ui.theme.InkLighter
import ui.theme.RegularTightMedium
import ui.theme.SmallTightRegular
import ui.util.imagePainter

@Composable
fun MyRequests() {
    ScreenLayout {
        NavBarLarge(
            title = { Text("Session Requests") },
        )
//        depend on the pattern from MySessions
//        Row () { }
        LazyColumn {
            itemsIndexed(SessionRepository.myRequests) { index, request ->
                if (index > 0) {
                    Divider()
                }
                RequestRow(request)
            }
        }
    }
}

@Composable
fun RequestRow(request: GetMyRequests) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.dp)
            .padding(start = 24.dp, end = 12.dp),
    ) {
        Image(
            painter = imagePainter(request.creator_profile_pic_url),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = request.topic,
                color = InkDarkest,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = RegularTightMedium
            )
            Text(
                text = request.start_time,
                color = InkLighter,
                style = SmallTightRegular
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    onClick = {
                        SessionRepository.updateSessionStatus(sessionId = request.id, status = Session.Status.ACCEPTED)
                    }
                ) {
                    Text(text = "Accept")
                }
                Spacer(Modifier.width(12.dp))
                CustomButton(
                    onClick = {
                        SessionRepository.updateSessionStatus(sessionId = request.id, status = Session.Status.DECLINED)
                    },
                    colors = buttonColorsSecondary()
                ) {
                    Text(text = "Decline")
                }
            }
        }
    }
}
