package link.limecode.reddit.lite.desktop.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import link.limecode.reddit.lite.components.Logo
import link.limecode.reddit.lite.desktop.ui.theme.DesktopTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.*

@ExperimentalMaterialApi
@Composable
fun RedditDesktopAppBar(
    navToChat: () -> Unit,
    navToNotification: () -> Unit,
    navToProfile: () -> Unit,
    navToHome: () -> Unit,
    navToLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.height(56.dp)
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            },
        color = MaterialTheme.colors.surface
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

            Box(modifier = Modifier.wrapContentSize()) {
                val profileMenuExpanded = remember { mutableStateOf(false) }
                DeskTopAppBarMenuItem(Res.drawable.person_outline) {
                    profileMenuExpanded.value = true
                    //navToProfile()
                }

                DropdownMenu(
                    expanded = profileMenuExpanded.value,
                    onDismissRequest = {
                        profileMenuExpanded.value = false
                    }
                ) {
                    DropdownMenuItem(onClick = { navToLogin() }) {
                        Text(stringResource(Res.string.login_screen_label))
                    }
                }
            }
        }
    }
}

@Composable
fun DeskTopAppBarMenuItem(resource: DrawableResource, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(width = 40.dp, height = 40.dp)
    ) {
        Icon(
            imageVector = vectorResource(resource),
            contentDescription = null,
            modifier = Modifier.size(width = 24.dp, height = 24.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
private fun PreviewRedditDesktopAppBar() {
    DesktopTheme {
        RedditDesktopAppBar(
            navToChat = {},
            navToHome = {},
            navToNotification = {},
            navToProfile = {},
            navToLogin = {}
        )
    }
}