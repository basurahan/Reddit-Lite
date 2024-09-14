package link.limecode.reddit.lite.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun RedditAppBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        windowInsets = WindowInsets.statusBars
    )
}