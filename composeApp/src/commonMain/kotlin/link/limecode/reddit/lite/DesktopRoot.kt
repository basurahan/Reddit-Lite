package link.limecode.reddit.lite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.desktop.ui.components.RedditDesktopAppBar

@Composable
fun DesktopRoot() {
    Scaffold(
        topBar = {
            RedditDesktopAppBar()
        },
        modifier = Modifier.fillMaxSize()
    ) {
        
    }
}