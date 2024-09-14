package link.limecode.reddit.lite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import link.limecode.reddit.lite.ui.theme.RedditLiteTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.home_tab_label

@Composable
fun RedditMobileAppBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        windowInsets = WindowInsets.statusBars
    )
}

@Composable
@Preview
private fun PreviewRedditAppBar() {
    RedditLiteTheme {
        RedditMobileAppBar(stringResource(Res.string.home_tab_label))
    }
}