package link.limecode.reddit.lite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import link.limecode.reddit.lite.ui.components.RedditBottomNavBar
import link.limecode.reddit.lite.ui.components.RedditDesktopAppBar
import link.limecode.reddit.lite.ui.theme.LocalPlatformTarget
import link.limecode.reddit.lite.ui.theme.RedditLiteTheme
import link.limecode.reddit.lite.util.isDesktop
import link.limecode.reddit.lite.util.isMobile
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    RedditLiteTheme {
        Root()
    }
}

@Composable
fun Root() {
    val navController = rememberNavController()
    val currentSelectedTab by navController.currentTabAsState()
    val platform = LocalPlatformTarget.current

    Scaffold(
        topBar = {
            if (currentSelectedTab != null && platform.target.isDesktop()) {
                RedditDesktopAppBar()
            }
        },
        bottomBar = {
            if (currentSelectedTab != null && platform.target.isMobile()) {
                RedditBottomNavBar(currentSelectedTab!!) { selected ->
                    navController.navigate(selected.asRoute()) {
                        launchSingleTop = true
                        restoreState = true

                        navController.graph.findStartDestination().route?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                    }
                }
            }
        }
    ) {
        AppNavigation(
            navController = navController,
            modifier = Modifier.fillMaxSize().padding(it)
        )
    }
}