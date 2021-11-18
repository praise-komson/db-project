package ui.screens

import PlaceholderDescription
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import entity.Service
import ui.components.*
import ui.theme.InkBase
import ui.theme.SmallNormalRegular
import kotlin.math.ceil

@Composable
fun NewSession(
    service: Service
) {
    Column {
        ScreenLayout(modifier = Modifier.weight(1f)) {
            NavBarStandard(title = { Text("New session") })
            NewSessionHeader(service)
            Text(
                text = service.description,
                modifier = Modifier.padding(horizontal = 24.dp),
                color = InkBase,
                style = SmallNormalRegular
            )
            NewSessionConfiguration()
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            PriceSummary(service.fee)
        }
    }
}

@Composable
fun NewSessionConfiguration() {
    Column(modifier = Modifier.padding(24.dp)) {
        CustomTextField(
            value = "13 Dec 2021",
            onValueChange = {},
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        TimeSelector()
    }
}

@Composable
fun TimeSelector() {
    TimeSelectorLayout(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        CustomTextField(
            value = "14:00",
            onValueChange = {},
            label = { Text("Start Time") },
            shape = RoundedCornerShape(
                topStart = 8.dp,
                bottomStart = 8.dp
            )
        )
        CustomTextField(
            value = "16:00",
            onValueChange = {},
            label = { Text("End Time") },
            shape = RoundedCornerShape(
                topEnd = 8.dp,
                bottomEnd = 8.dp
            )
        )
    }
}

@Composable
fun TimeSelectorLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val overlapWidth = with(LocalDensity.current) { ceil(1.dp.toPx()).toInt() }
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val firstWidth = width / 2
        val secondWidth = width - firstWidth + overlapWidth
        val placeable0 = measurables[0].measure(constraints.copy(minWidth = firstWidth, maxWidth = firstWidth))
        val placeable1 = measurables[1].measure(constraints.copy(minWidth = secondWidth, maxWidth = secondWidth))

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable0.placeRelative(x = 0, y = 0)
            placeable1.placeRelative(x = firstWidth - overlapWidth, y = 0)
        }
    }
}
