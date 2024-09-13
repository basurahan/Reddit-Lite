package link.limecode.reddit.lite.ui.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditAppBar

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { RedditAppBar("Home") },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}