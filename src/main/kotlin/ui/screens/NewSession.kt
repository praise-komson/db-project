package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import entity.Service
import ui.components.CustomTextField
import ui.components.NavBarStandard
import ui.components.NewSessionHeader
import ui.components.ScreenLayout
import ui.theme.*
import kotlin.math.ceil

@Composable
fun NewSession(
    service: Service
) {
    var startTime by remember { mutableStateOf("08:00") }
    var duration by remember { mutableStateOf("1") }
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
            NewSessionConfiguration(startTime, { value ->
                if (value.matches(Regex("\\d{0,2}:\\d{0,2}"))) {
                    startTime = value
                }
            }, duration, { value ->
                if (value.matches(Regex("\\d*"))) {
                    duration = value
                }
            })
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            PriceSummary(service.fee * Integer.parseInt(duration.ifEmpty { "0" }))
        }
    }
}

@Composable
fun NewSessionConfiguration(
    startTime: String,
    onStartTimeChange: (String) -> Unit,
    duration: String,
    onDurationChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(24.dp)) {
        CustomTextField(
            value = "13 Dec 2021",
            onValueChange = {},
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        TimeSelectorLayout(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            CustomTextField(
                value = startTime,
                onValueChange = onStartTimeChange,
                label = { Text("Start Time") },
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    bottomStart = 8.dp
                )
            )
            CustomTextField(
                value = duration,
                onValueChange = onDurationChange,
                label = { Text("Duration (hrs)") },
                shape = RoundedCornerShape(
                    topEnd = 8.dp,
                    bottomEnd = 8.dp
                )
            )
        }
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