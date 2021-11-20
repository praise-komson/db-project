package ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.theme.*

@Composable
fun buttonColorsPrimary() = ButtonDefaults.buttonColors(
    backgroundColor = PrimaryBase,
    contentColor = SkyWhite
)

@Composable
fun buttonColorsSecondary() = ButtonDefaults.buttonColors(
    backgroundColor = PrimaryLightest,
    contentColor = PrimaryBase
)

@Composable
fun buttonColorsTransparent() = ButtonDefaults.buttonColors(
    backgroundColor = Color.Transparent,
    contentColor = InkDarker
)

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = buttonColorsPrimary(),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        elevation = null,
        shape = CircleShape,
        colors = colors
    ) {
        CompositionLocalProvider(LocalTextStyle provides RegularNoneMedium) {
            content()
        }
    }
}
