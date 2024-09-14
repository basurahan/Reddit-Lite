package link.limecode.reddit.lite.ui.pages.home.tabs.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditAppBar
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.home_tab_label

@Composable
fun HomeTabScreen() {
    Scaffold(
        topBar = { RedditAppBar(stringResource(Res.string.home_tab_label)) },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}