package link.limecode.reddit.lite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import link.limecode.reddit.lite.desktop.DesktopNavigation
import link.limecode.reddit.lite.desktop.DesktopNavigationRoutes
import link.limecode.reddit.lite.desktop.ui.components.RedditDesktopAppBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DesktopRoot() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            if (navController.currentDestination?.route != DesktopNavigationRoutes.LOGIN.route) {
                RedditDesktopAppBar(
                    navToHome = { navController.navigate(DesktopNavigationRoutes.HOME.route) },
                    navToChat = { navController.navigate(DesktopNavigationRoutes.CHAT.route) },
                    navToNotification = { navController.navigate(DesktopNavigationRoutes.NOTIFICATION.route) },
                    navToProfile = { navController.navigate(DesktopNavigationRoutes.PROFILE.route) },
                    navToLogin = { navController.navigate(DesktopNavigationRoutes.LOGIN.route) }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        DesktopNavigation(
            navController = navController,
            modifier = Modifier.fillMaxSize()
        )
    }
}