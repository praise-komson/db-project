package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.LargeNoneMedium
import ui.theme.PrimaryDark
import ui.theme.RegularNormalMedium
import ui.theme.Title3

@Composable
fun PriceSummary(
    price: Long
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .height(32.dp)
        ) {
            Text(
                text = "Total Price",
                modifier = Modifier.weight(1f),
                style = LargeNoneMedium
            )
            Text(
                text = "$price ",
                color = PrimaryDark,
                style = Title3
            )
            Text(
                text = "Doji coins",
                color = PrimaryDark,
                style = RegularNormalMedium
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = CircleShape
        ) {
            Text("Schedule")
        }
    }
}
