package screens

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.NavBarLarge
import theme.InkDarkest
import theme.InkLighter
import theme.RegularTightMedium
import theme.SmallTightRegular

@Composable
fun Home(
    onNewSessionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
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
        LazyColumn {
            items(10) { index ->
                if (index > 0) {
                    Divider()
                }
                SessionRow()
            }
        }
    }
}

@Composable
fun SessionRow() {
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
                text = "How to read indicators",
                color = InkDarkest,
                style = RegularTightMedium
            )
            Text(
                text = "13 Dec 2021 14:00 - 15:00",
                color = InkLighter,
                style = SmallTightRegular
            )
        }
        Spacer(Modifier.width(12.dp))
        IconButton(onClick = { }) {
            Icon(Icons.Default.MoreVert, "")
        }
    }
}

