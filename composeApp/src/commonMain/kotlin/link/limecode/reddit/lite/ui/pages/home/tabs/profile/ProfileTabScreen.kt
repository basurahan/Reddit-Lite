package link.limecode.reddit.lite.ui.pages.home.tabs.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import link.limecode.reddit.lite.ui.components.RedditAppBar
import org.jetbrains.compose.resources.stringResource
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.login_screen_label
import redditlite.composeapp.generated.resources.profile_tab_label

@Composable
fun ProfileTabScreen(
    navToLogin: () -> Unit
) {
    Scaffold(
        topBar = { RedditAppBar(stringResource(Res.string.profile_tab_label)) },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navToLogin() }) {
                Text(stringResource(Res.string.login_screen_label))
            }
        }
    }
}