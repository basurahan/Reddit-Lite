package link.limecode.reddit.lite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import link.limecode.reddit.lite.mobile.ui.theme.MobileTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import redditlite.composeapp.generated.resources.Res
import redditlite.composeapp.generated.resources.app_name
import redditlite.composeapp.generated.resources.compose_multiplatform

@Composable
fun Logo(height: Dp, onClick: () -> Unit) {
    Row(
        modifier = Modifier.wrapContentHeight().clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            modifier = Modifier.size(height)
        )
        
        Text(
            text = stringResource(Res.string.app_name),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
@Preview
fun PreviewLogo() {
    MobileTheme {
        Logo(40.dp) {}
    }
}