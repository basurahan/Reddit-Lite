package link.limecode.reddit.lite.desktop.ui.pages.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.create_post_tab_label

@Composable
fun DesktopCreatePostScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(Res.string.create_post_tab_label))
    }
}