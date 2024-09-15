package link.limecode.reddit.lite.desktop.ui.pages.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.chat_tab_label

@Composable
fun DesktopChatScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(Res.string.chat_tab_label))
    }
}