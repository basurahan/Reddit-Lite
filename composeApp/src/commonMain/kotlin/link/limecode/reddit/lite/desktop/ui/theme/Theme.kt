package link.limecode.reddit.lite.desktop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import link.limecode.reddit.lite.client.getPlatform

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
fun DesktopTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
        MaterialTheme(
            colors = colors,
            content = content
        )
    }
}