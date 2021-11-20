package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ui.theme.InkDarkest

@Composable
fun PopupScrim(
    onDismissRequest: () -> Unit
) {
    Spacer(modifier = Modifier
        .fillMaxSize()
        .background(InkDarkest.copy(alpha = 0.7f))
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onDismissRequest
        )
    )
}
