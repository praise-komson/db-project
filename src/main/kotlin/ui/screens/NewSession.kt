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
import controller.UserController
import db.DatabaseHelper
import entity.Service
import entity.Session
import repository.SessionRepository
import repository.TransactionSourceRepository
import ui.components.CustomTextField
import ui.components.NavBarStandard
import ui.components.NewSessionHeader
import ui.components.ScreenLayout
import ui.theme.*
import kotlin.math.ceil

@Composable
fun NewSession(
    service: Service,
    onFinishNewSession: () -> Unit,
) {
    var startDate by remember { mutableStateOf("2021-11-30") }
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
            NewSessionConfiguration(startDate, { value ->
                if (value.matches(Regex("\\d{0,4}-\\d{0,2}-\\d{0,2}"))) {
                    startDate = value
                }
            }, startTime, { value ->
                if (value.matches(Regex("\\d{0,2}:\\d{0,2}"))) {
                    startTime = value
                }
            }, duration, { value ->
                if (value.matches(Regex("\\d{0,2}"))) {
                    duration = value
                }
            })
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            PriceSummary(service.fee * Integer.parseInt(duration.ifEmpty { "0" })) {
                val durationInt = Integer.parseInt(duration.ifEmpty { "0" })
                if (durationInt <= 0) {
                    return@PriceSummary
                }
                DatabaseHelper.database.transaction {
                    afterCommit(onFinishNewSession)
                    afterRollback {
                        print("ERROR HAPPENED")
                        onFinishNewSession()
                    }
                    val sourceId = TransactionSourceRepository.createTransactionSource()
                    val username = UserController.username!!
                    var session = Session(
                        id = 1,
                        meetingProviderId = "1",
                        fee = service.fee,
                        coinOnHold = 0,
                        status = Session.Status.PENDING,
                        topic = service.sname,
                        duration = durationInt,
                        startTime = String.format("%s %s:00", startDate, startTime),
                        sourceId = sourceId.toInt(),
                        creatorId = username,
                        expertId = service.expertId,
                        serviceName = service.sname,
                    )
                    session = SessionRepository.insertSession(session)
                    // TODO: add friend?
                    SessionRepository.insertSessionParticipant(session.id, username)
                    // TODO: call stored procedure to transfer coin
                }
            }
        }
    }
}

@Composable
fun NewSessionConfiguration(
    startDate: String,
    onStartDateChange: (String) -> Unit,
    startTime: String,
    onStartTimeChange: (String) -> Unit,
    duration: String,
    onDurationChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(24.dp)) {
        CustomTextField(
            value = startDate,
            onValueChange = onStartDateChange,
            label = { Text("Date (yyyy-mm-dd)") },
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
    price: Long,
    onClickSchedule: () -> Unit,
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
            onClick = onClickSchedule,
            modifier = Modifier
                .fillMaxWidth(),
            shape = CircleShape
        ) {
            Text("Schedule")
        }
    }
}