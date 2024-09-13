package link.limecode.reddit.lite.ui.pages.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditAppBar

@Composable
fun LoginScreen() {
    Scaffold(
        topBar = { RedditAppBar("Login") },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}