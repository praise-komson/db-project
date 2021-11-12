package ui.screens

import PlaceholderDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.components.NavBarStandard
import ui.theme.*

@Composable
fun BrowseServices() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        NavBarStandard(
            title = { Text("New session") }
        )
        LazyColumn {
            items(10) { index ->
                if (index > 0) {
                    Divider()
                }
                ServiceRow()
            }
        }
    }
}

@Composable
fun ServiceRow() {
    Column(
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "How to read indicators",
                    style = LargeTightBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "by ",
                        color = InkLighter,
                        style = SmallTightRegular
                    )
                    Text(
                        text = "Rick Astley",
                        color = InkLighter,
                        style = SmallTightMedium
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "250",
                    color = PrimaryDark,
                    style = LargeNormalBold
                )
                Text(
                    text = "/hr/person",
                    color = PrimaryBase,
                    style = TinyNormalMedium
                )
            }
        }
        Text(
            text = PlaceholderDescription,
            color = InkBase,
            style = SmallNormalRegular
        )
    }
}
