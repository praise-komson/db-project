package ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ui.theme.*

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderWidth by animateDpAsState(if (isFocused) 2.dp else 1.dp)
    val borderColor by animateColorAsState(if (isFocused) PrimaryBase else SkyLight)
    val labelColor by animateColorAsState(if (isFocused) PrimaryBase else InkLighter)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .zIndex(if (borderWidth > 1.5.dp) 1f else 0f),
        enabled = enabled,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .border(borderWidth, borderColor, shape)
                    .padding(vertical = 10.dp, horizontal = 16.dp)
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides labelColor,
                    LocalTextStyle provides TinyNoneRegular
                ) {
                    label()
                }
                CompositionLocalProvider(
                    LocalContentColor provides InkDarkest,
                    LocalTextStyle provides RegularNoneRegular
                ) {
                    innerTextField()
                }
            }
        }
    )
}
