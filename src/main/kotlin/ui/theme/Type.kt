package ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

val Inter = FontFamily(
    Font(
        resource = "Inter-Regular.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    ),
    Font(
        resource = "Inter-Medium.ttf",
        weight = FontWeight.W500,
        style = FontStyle.Normal
    ),
    Font(
        resource = "Inter-Bold.ttf",
        weight = FontWeight.W700,
        style = FontStyle.Normal
    ),
)

val Title2 = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold
)

val LargeRegular = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal
)

val LargeNormalBold = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)

val LargeTightBold = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)

val RegularTightMedium = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium
)

val SmallNormalRegular = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

val SmallTightRegular = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

val SmallTightMedium = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium
)

val TinyNormalMedium = TextStyle(
    color = InkDarkest,
    fontFamily = Inter,
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium
)
