package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
            items(2) { index ->
                if (index > 0) {
                    Divider()
                }
                RequestRow()
            }
        }
    }
}

@Composable
fun RequestRow() {
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
                text = "How to read indicators",
                color = InkDarkest,
                style = RegularTightMedium
            )
            Text(
                text = "13 Dec 2021 14:00 - 15:00",
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
                    onClick = { },
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
                    onClick = { },
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

