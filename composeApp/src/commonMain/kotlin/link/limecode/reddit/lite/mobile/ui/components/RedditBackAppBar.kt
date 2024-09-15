package link.limecode.reddit.lite.mobile.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import link.limecode.reddit.lite.mobile.ui.theme.MobileTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RedditBackAppBar(title: String, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            } 
        },
        windowInsets = WindowInsets.statusBars
    )
}

@Composable
@Preview
private fun PreviewRedditBackAppBar() {
    MobileTheme {
        RedditBackAppBar("Sample") {}
    }
}