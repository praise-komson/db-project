package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ui.theme.InkLighter
import ui.theme.SmallTightMedium
import ui.theme.SmallTightRegular
import ui.util.imagePainter

@Composable
fun ExpertChip(
    modifier: Modifier = Modifier,
    expertName: String,
    profilePic: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = imagePainter(profilePic),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "by ",
            color = InkLighter,
            style = SmallTightRegular
        )
        Text(
            text = expertName,
            color = InkLighter,
            style = SmallTightMedium
        )
    }
}
