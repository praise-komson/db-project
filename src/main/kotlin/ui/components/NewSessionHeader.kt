package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.LargeNormalBold
import ui.theme.PrimaryDark
import ui.theme.TinyNormalMedium
import ui.theme.Title3

@Composable
fun NewSessionHeader() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = "How to read indicators",
            style = Title3
        )
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExpertChip(modifier = Modifier.weight(1f))
            Row {
                Text(
                    text = "250",
                    modifier = Modifier.alignByBaseline(),
                    color = PrimaryDark,
                    style = LargeNormalBold
                )
                Text(
                    text = "/hr/person",
                    modifier = Modifier.alignByBaseline(),
                    color = PrimaryDark,
                    style = TinyNormalMedium
                )
            }
        }
    }
}
