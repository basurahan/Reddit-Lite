package link.limecode.reddit.lite.ui.pages.home.tabs.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditMobileAppBar
import link.limecode.reddit.lite.ui.theme.LocalPlatformTarget
import link.limecode.reddit.lite.util.isMobile
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.notification_tab_label

@Composable
fun NotificationTabScreen() {
    val platform = LocalPlatformTarget.current
    Scaffold(
        topBar = { if (platform.target.isMobile()) RedditMobileAppBar(stringResource(Res.string.notification_tab_label)) },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}