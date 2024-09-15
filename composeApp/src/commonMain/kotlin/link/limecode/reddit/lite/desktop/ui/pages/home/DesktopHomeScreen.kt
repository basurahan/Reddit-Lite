package link.limecode.reddit.lite.desktop.ui.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.home_tab_label

@Composable
fun DesktopHomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(Res.string.home_tab_label))
    }
}