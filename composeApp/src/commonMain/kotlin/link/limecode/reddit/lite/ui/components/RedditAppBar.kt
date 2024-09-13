package link.limecode.reddit.lite.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun RedditAppBar(title: String) {
    TopAppBar(
        title = { Text(title) }
    )
}