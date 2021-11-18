package ui.screens

import PlaceholderDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.ServiceController
import entity.Service
import ui.components.ExpertChip
import ui.components.NavBarStandard
import ui.components.ScreenLayout
import ui.theme.*

@Composable
fun BrowseServices(
    onSelectService: (expertUsername: String, serviceName: String) -> Unit
) {
    ScreenLayout {
        NavBarStandard(
            title = { Text("New session") }
        )
        LazyColumn {
            itemsIndexed(ServiceController.services) { index, service ->
                if (index > 0) {
                    Divider()
                }
                ServiceRow(
                    modifier = Modifier
                        .clickable { onSelectService("dummy_expert", "dummy_service") },
                    service

                )
            }
        }
    }
}

@Composable
fun ServiceRow(
    modifier: Modifier = Modifier,
    service: Service
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = service.sname,
                    style = LargeTightBold
                )
                ExpertChip(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    expertName = service.expertId
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = service.fee.toString(),
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
            text = service.description,
            color = InkBase,
            style = SmallNormalRegular
        )
    }
}
