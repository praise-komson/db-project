package ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import ui.theme.SkyWhite

@Composable
fun BottomSheet(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Popup {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PopupScrim(onDismissRequest)
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                        .background(SkyWhite)
                ) {
                    content()
                }
            }
        }
    }
}
