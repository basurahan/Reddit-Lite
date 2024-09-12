package link.limecode.reddit.lite.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun RedditBackAppBar(onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text("Reddit") },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            } 
        }
    )
}