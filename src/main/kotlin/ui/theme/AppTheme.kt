package ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        shapes = Shapes(
            small = RoundedCornerShape(8.dp)
        )
    ) {
        content()
    }
}
