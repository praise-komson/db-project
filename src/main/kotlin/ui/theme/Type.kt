@file:Suppress("unused")

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

private val Base = TextStyle(fontFamily = Inter)

val Title1 = Base.copy(
    fontSize = 48.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 56.sp
)

val Title2 = Base.copy(
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 36.sp
)

val Title3 = Base.copy(
    fontFamily = Inter,
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 32.sp
)

private val LargeNoneBase = Base.copy(
    fontSize = 18.sp,
    lineHeight = 18.sp
)
val LargeNoneRegular = LargeNoneBase.copy(fontWeight = FontWeight.Normal)
val LargeNoneMedium = LargeNoneBase.copy(fontWeight = FontWeight.Medium)
val LargeNoneBold = LargeNoneBase.copy(fontWeight = FontWeight.Bold)

private val LargeTightBase = Base.copy(
    fontSize = 18.sp,
    lineHeight = 20.sp
)
val LargeTightRegular = LargeTightBase.copy(fontWeight = FontWeight.Normal)
val LargeTightMedium = LargeTightBase.copy(fontWeight = FontWeight.Medium)
val LargeTightBold = LargeTightBase.copy(fontWeight = FontWeight.Bold)

private val LargeNormalBase = Base.copy(
    fontSize = 18.sp,
    lineHeight = 24.sp
)
val LargeNormalRegular = LargeNormalBase.copy(fontWeight = FontWeight.Normal)
val LargeNormalMedium = LargeNormalBase.copy(fontWeight = FontWeight.Medium)
val LargeNormalBold = LargeNormalBase.copy(fontWeight = FontWeight.Bold)

private val RegularNoneBase = Base.copy(
    fontSize = 16.sp,
    lineHeight = 16.sp
)
val RegularNoneRegular = RegularNoneBase.copy(fontWeight = FontWeight.Normal)
val RegularNoneMedium = RegularNoneBase.copy(fontWeight = FontWeight.Medium)
val RegularNoneBold = RegularNoneBase.copy(fontWeight = FontWeight.Bold)

private val RegularTightBase = Base.copy(
    fontSize = 16.sp,
    lineHeight = 20.sp
)
val RegularTightRegular = RegularTightBase.copy(fontWeight = FontWeight.Normal)
val RegularTightMedium = RegularTightBase.copy(fontWeight = FontWeight.Medium)
val RegularTightBold = RegularTightBase.copy(fontWeight = FontWeight.Bold)

private val RegularNormalBase = Base.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val RegularNormalRegular = RegularNormalBase.copy(fontWeight = FontWeight.Normal)
val RegularNormalMedium = RegularNormalBase.copy(fontWeight = FontWeight.Medium)
val RegularNormalBold = RegularNormalBase.copy(fontWeight = FontWeight.Bold)

private val SmallNoneBase = Base.copy(
    fontSize = 14.sp,
    lineHeight = 14.sp
)
val SmallNoneRegular = SmallNoneBase.copy(fontWeight = FontWeight.Normal)
val SmallNoneMedium = SmallNoneBase.copy(fontWeight = FontWeight.Medium)
val SmallNoneBold = SmallNoneBase.copy(fontWeight = FontWeight.Bold)

private val SmallTightBase = Base.copy(
    fontSize = 14.sp,
    lineHeight = 16.sp
)
val SmallTightRegular = SmallTightBase.copy(fontWeight = FontWeight.Normal)
val SmallTightMedium = SmallTightBase.copy(fontWeight = FontWeight.Medium)
val SmallTightBold = SmallTightBase.copy(fontWeight = FontWeight.Bold)

private val SmallNormalBase = Base.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp
)
val SmallNormalRegular = SmallNormalBase.copy(fontWeight = FontWeight.Normal)
val SmallNormalMedium = SmallNormalBase.copy(fontWeight = FontWeight.Medium)
val SmallNormalBold = SmallNormalBase.copy(fontWeight = FontWeight.Bold)

private val TinyNoneBase = Base.copy(
    fontSize = 12.sp,
    lineHeight = 12.sp
)
val TinyNoneRegular = TinyNoneBase.copy(fontWeight = FontWeight.Normal)
val TinyNoneMedium = TinyNoneBase.copy(fontWeight = FontWeight.Medium)
val TinyNoneBold = TinyNoneBase.copy(fontWeight = FontWeight.Bold)

private val TinyTightBase = Base.copy(
    fontSize = 12.sp,
    lineHeight = 14.sp
)
val TinyTightRegular = TinyTightBase.copy(fontWeight = FontWeight.Normal)
val TinyTightMedium = TinyTightBase.copy(fontWeight = FontWeight.Medium)
val TinyTightBold = TinyTightBase.copy(fontWeight = FontWeight.Bold)

private val TinyNormalBase = Base.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp
)
val TinyNormalRegular = TinyNormalBase.copy(fontWeight = FontWeight.Normal)
val TinyNormalMedium = TinyNormalBase.copy(fontWeight = FontWeight.Medium)
val TinyNormalBold = TinyNormalBase.copy(fontWeight = FontWeight.Bold)
