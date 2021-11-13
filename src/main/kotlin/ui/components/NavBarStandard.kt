package ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.navigation.LocalBackPressedDispatcher
import ui.theme.LargeNoneRegular

@Composable
fun NavBarStandard(
    title: @Composable () -> Unit,
    showBackButton: Boolean = true
) {
    val backPressedDispatcher = LocalBackPressedDispatcher.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        if (showBackButton) {
            IconButton(
                onClick = { backPressedDispatcher?.onBackPressed() },
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, "")
            }
        }
        Box(modifier = Modifier.align(Alignment.Center)) {
            CompositionLocalProvider(LocalTextStyle provides LargeNoneRegular) {
                title()
            }
        }
    }
}
