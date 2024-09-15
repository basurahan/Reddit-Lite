package link.limecode.reddit.lite.desktop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import link.limecode.reddit.lite.components.Logo
import link.limecode.reddit.lite.desktop.ui.theme.DesktopTheme
import link.limecode.reddit.lite.desktop.ui.theme.LocalCustomColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.chatbubble_ellipses_outline
import redditlite.composeapp.generated.resources.notifications_outline
import redditlite.composeapp.generated.resources.person_outline

@Composable
fun RedditDesktopAppBar(
    navToChat: () -> Unit,
    navToNotification: () -> Unit,
    navToProfile: () -> Unit,
    navToHome: () -> Unit
) {
    Surface(
        modifier = Modifier.height(65.dp).fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Row(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Logo(40.dp) { navToHome() }
            Spacer(Modifier.weight(1f).fillMaxHeight())
            DeskTopAppBarMenuItem(Res.drawable.chatbubble_ellipses_outline) { navToChat() }
            Spacer(Modifier.width(16.dp).fillMaxHeight())
            DeskTopAppBarMenuItem(Res.drawable.notifications_outline) { navToNotification() }
            Spacer(Modifier.width(16.dp).fillMaxHeight())
            DeskTopAppBarMenuItem(Res.drawable.person_outline) {
                navToProfile()
            }
        }
    }
}

@Composable
fun DeskTopAppBarMenuItem(resource: DrawableResource, onClick: () -> Unit) {
    val customColors = LocalCustomColors.current
    
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(width = 40.dp, height = 40.dp)
            .background(color = customColors.onSurfaceButtonBackground, shape = CircleShape)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
    ) {
        Icon(
            imageVector = vectorResource(resource),
            contentDescription = null,
            modifier = Modifier.size(width = 24.dp, height = 24.dp)
        )
    }
}

@Composable
@Preview
private fun PreviewRedditDesktopAppBar() {
    DesktopTheme {
        RedditDesktopAppBar(
            navToChat = {},
            navToHome = {},
            navToNotification = {},
            navToProfile = {}
        )
    }
}