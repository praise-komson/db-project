package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import controller.SessionController
import entity.Session
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.theme.*
import java.awt.TextArea

@Composable
fun MyRequests() {
    ScreenLayout {
        NavBarLarge(
            title = { Text("Session Requests") },
        )
//        depend on the pattern from MySessions
//        Row () { }
        LazyColumn {
            itemsIndexed(SessionController.myRequests) { index, session ->
                if (index > 0) {
                    Divider()
                }
                RequestRow(session)
            }
        }
    }
}

@Composable
fun RequestRow(session: Session) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.dp)
            .padding(start = 24.dp, end = 12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
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
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                , verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        session.status = "ACCEPTED"
                        session.save()
                    },
                    shape = CircleShape
                ) {
                    Text (
                        text = "Accept",
                        color = PrimaryLightest,
                        style = RegularTightMedium,
                    )
                }
                Spacer(Modifier.width(12.dp))
                Button(
                    onClick = {
                        session.status = "DECLINED"
                        session.save()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = CircleShape,
                ) {
                    Text (
                        text = "Decline",
                        color = PrimaryDark,
                        style = RegularTightMedium,
                    )
                }
            }
        }
    }
}
