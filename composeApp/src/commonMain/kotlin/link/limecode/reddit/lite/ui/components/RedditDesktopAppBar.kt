package link.limecode.reddit.lite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import link.limecode.reddit.lite.ui.theme.RedditLiteTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.home_tab_label

@Composable
fun RedditDesktopAppBar() {
    Surface(
        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary,
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp).wrapContentHeight().fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {},
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Text(stringResource(Res.string.home_tab_label))
            }

            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.MailOutline,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewRedditDesktopAppBar() {
    RedditLiteTheme {
        RedditDesktopAppBar()
    }
}