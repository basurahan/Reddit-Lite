package link.limecode.reddit.lite.ui.pages.home.tabs.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditAppBar
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.chat_tab_label

@Composable
fun ChatTabScreen() {
    Scaffold(
        topBar = { RedditAppBar(stringResource(Res.string.chat_tab_label)) },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}