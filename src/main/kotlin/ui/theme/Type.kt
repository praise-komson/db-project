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
    fontFamily = Inter,
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold
)

val Title3 = TextStyle(
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 24.sp
)

val LargeRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal
)

val LargeNoneMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium
)

val LargeNormalBold = TextStyle(
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 24.sp
)

val LargeTightBold = TextStyle(
    fontFamily = Inter,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)

val RegularTightMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium
)

val RegularNoneRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp
)

val RegularNormalMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 24.sp
)

val SmallNormalRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

val SmallTightRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

val SmallTightMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium
)

val TinyNoneRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 12.sp
)

val TinyNormalMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 12.sp
)
