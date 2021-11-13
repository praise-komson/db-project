package ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.navigation.Configuration
import ui.theme.InkLight
import ui.theme.PrimaryBase
import ui.theme.SkyDark
import ui.theme.TinyNoneRegular

val targets = listOf(
    BottomNavTarget(Configuration.MySessions, "icons/calendar.svg", "Sessions"),
    BottomNavTarget(Configuration.Profile, "icons/user.svg", "Profile"),
)

@Composable
fun BottomNav(
    currentConfig: Configuration,
    onSetConfig: (Configuration) -> Unit
) {
    Surface(
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            targets.forEach {
                BottomNavItem(
                    onClick = { onSetConfig(it.config) },
                    modifier = Modifier.weight(1f),
                    target = it,
                    isActive = currentConfig == it.config
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    target: BottomNavTarget,
    isActive: Boolean
) {
    val iconColor by animateColorAsState(if (isActive) PrimaryBase else SkyDark)
    val labelColor by animateColorAsState(if (isActive) PrimaryBase else InkLight)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, color = labelColor),
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(target.iconPath), "", tint = iconColor)
        Text(
            text = target.label,
            modifier = Modifier
                .padding(top = 4.dp),
            color = labelColor,
            style = TinyNoneRegular
        )
    }
}

data class BottomNavTarget(
    val config: Configuration,
    val iconPath: String,
    val label: String
)
