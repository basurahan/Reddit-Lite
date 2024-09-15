package link.limecode.reddit.lite.mobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import redditlite.composeapp.generated.resources.*

private val LightColors = lightColors(
    primary = primaryColor,
    primaryVariant = primaryVariantColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryVariantColor
)

private val DarkColors = darkColors(
    primary = primaryColor,
    primaryVariant = primaryVariantColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryVariantColor
)

@Composable
fun MobileTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val poppins = FontFamily(
        Font(
            resource = Res.font.poppins_extralight,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.poppins_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.poppins_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.poppins_bold,
            weight = FontWeight.Bold
        )
    )

    val typography = remember {
        Typography(
            defaultFontFamily = poppins,
            h1 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Light,
                fontSize = 96.sp,
                letterSpacing = (-1.5).sp
            ),
            h2 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Light,
                fontSize = 60.sp,
                letterSpacing = (-0.5).sp
            ),
            h3 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp,
                letterSpacing = 0.sp
            ),
            h4 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                letterSpacing = 0.25.sp
            ),
            h5 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                letterSpacing = 0.sp
            ),
            h6 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp
            ),
            subtitle1 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = 0.15.sp
            ),
            subtitle2 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.1.sp
            ),
            body1 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = 0.5.sp
            ),
            body2 = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                letterSpacing = 0.25.sp
            ),
            button = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 1.25.sp
            ),
            caption = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                letterSpacing = 0.4.sp
            ),
            overline = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                letterSpacing = 1.5.sp
            )
        )
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography
    )
}