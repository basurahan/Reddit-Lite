package link.limecode.reddit.lite.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import link.limecode.reddit.lite.client.getPlatform
import link.limecode.reddit.lite.util.isMobile
import org.jetbrains.compose.resources.Font
import redditlite.composeapp.generated.resources.*
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.poppins_bold
import redditlite.composeapp.generated.resources.poppins_extralight
import redditlite.composeapp.generated.resources.poppins_regular

val LocalPlatformTarget = staticCompositionLocalOf { getPlatform() }

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
fun RedditLiteTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val platform = remember { getPlatform() }
    
    val colors = if (isDarkTheme) {
        DarkColors
    } else {
        LightColors
    }
    
    CompositionLocalProvider(LocalPlatformTarget provides platform) {
        /**
         * TODO: there is a bug in wesm where variable font is not working [https://youtrack.jetbrains.com/issue/CMP-4635/Wasm-Variable-fonts-are-displayed-as]
         * So we need to remove font here
         **/
        if (platform.target.isMobile()) {
            MobileTheme(colors) {
                content()
            }
        } else {
            MaterialTheme(
                colors = colors,
                content = content
            )
        }
    }
}

@Composable
private fun MobileTheme(colors: Colors, content: @Composable () -> Unit) {
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