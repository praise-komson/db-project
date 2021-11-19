package ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.SessionController
import entity.Session
import ui.components.CustomButton
import ui.components.NavBarLarge
import ui.components.ScreenLayout
import ui.components.buttonColorsSecondary
import ui.theme.InkDarkest
import ui.theme.InkLighter
import ui.theme.RegularTightMedium
import ui.theme.SmallTightRegular

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
                CustomButton(
                    onClick = {
                        session.status = Session.Status.ACCEPTED
                        session.save()
                    }
                ) {
                    Text (text = "Accept")
                }
                Spacer(Modifier.width(12.dp))
                CustomButton(
                    onClick = {
                        session.status = Session.Status.DECLINED
                        session.save()
                    },
                    colors = buttonColorsSecondary()
                ) {
                    Text (text = "Decline")
                }
            }
        }
    }
}
