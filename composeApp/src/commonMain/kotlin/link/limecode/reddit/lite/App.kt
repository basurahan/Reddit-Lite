package link.limecode.reddit.lite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import link.limecode.reddit.lite.client.getPlatform
import link.limecode.reddit.lite.desktop.ui.theme.DesktopTheme
import link.limecode.reddit.lite.mobile.ui.theme.MobileTheme
import link.limecode.reddit.lite.uitl.isMobile

@Composable
fun App() {
    val platform = remember { getPlatform() }

    if (platform.target.isMobile()) {
        MobileTheme {
            MobileRoot()
        }
    } else {
        DesktopTheme {
            DesktopRoot()
        }
    }
}

