package link.limecode.reddit.lite.ui.pages.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditBackAppBar
import link.limecode.reddit.lite.ui.theme.LocalPlatformTarget
import link.limecode.reddit.lite.util.isMobile
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.login_screen_label

@Composable
fun LoginScreen(
    onNavBack: () -> Unit
) {
    val platform = LocalPlatformTarget.current
    Scaffold(
        topBar = { if (platform.target.isMobile()) RedditBackAppBar(stringResource(Res.string.login_screen_label)) { onNavBack() } },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}