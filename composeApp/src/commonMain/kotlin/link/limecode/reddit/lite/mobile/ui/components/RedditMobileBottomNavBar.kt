package link.limecode.reddit.lite.mobile.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import link.limecode.reddit.lite.mobile.HomeTabs
import link.limecode.reddit.lite.mobile.ui.theme.MobileTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.*

@Composable
fun RedditMobileBottomNavBar(
    currentSelectedTab: HomeTabs,
    onTabSelected: (HomeTabs) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        windowInsets = WindowInsets.navigationBars
    ) {
        BottomNavigationItem(
            selected = currentSelectedTab == HomeTabs.HOME,
            onClick = { onTabSelected(HomeTabs.HOME) },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.home_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(stringResource(Res.string.home_tab_label))
            }
        )

        BottomNavigationItem(
            selected = currentSelectedTab == HomeTabs.CHAT,
            onClick = { onTabSelected(HomeTabs.CHAT) },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.chatbubble_ellipses_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(stringResource(Res.string.chat_tab_label))
            }
        )

        BottomNavigationItem(
            selected = currentSelectedTab == HomeTabs.CREATE,
            onClick = { onTabSelected(HomeTabs.CREATE) },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.add_circle_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(stringResource(Res.string.create_post_tab_label))
            }
        )

        BottomNavigationItem(
            selected = currentSelectedTab == HomeTabs.NOTIFICATION,
            onClick = { onTabSelected(HomeTabs.NOTIFICATION) },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.notifications_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(stringResource(Res.string.notification_tab_label))
            }
        )

        BottomNavigationItem(
            selected = currentSelectedTab == HomeTabs.PROFILE,
            onClick = { onTabSelected(HomeTabs.PROFILE) },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.person_outline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(stringResource(Res.string.profile_tab_label))
            }
        )
    }
}

@Composable
@Preview
private fun PreviewRedditBottomNavBar() {
    MobileTheme {
        RedditMobileBottomNavBar(HomeTabs.CREATE) {}
    }
}