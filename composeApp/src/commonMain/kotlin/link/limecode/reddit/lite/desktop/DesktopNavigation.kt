package link.limecode.reddit.lite.desktop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import link.limecode.reddit.lite.desktop.ui.pages.chat.DesktopChatScreen
import link.limecode.reddit.lite.desktop.ui.pages.home.DesktopHomeScreen
import link.limecode.reddit.lite.desktop.ui.pages.login.DesktopLoginScreen
import link.limecode.reddit.lite.desktop.ui.pages.notification.DesktopNotificationScreen
import link.limecode.reddit.lite.desktop.ui.pages.profile.DesktopProfileScreen

enum class DesktopNavigationRoutes(val route: String) {
    HOME("route"),
    CHAT("chat"),
    NOTIFICATION("notification"),
    PROFILE("profile"),
    LOGIN("login")
}

@Composable
internal fun DesktopNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DesktopNavigationRoutes.HOME.route,
        modifier = modifier
    ) {
        composable(DesktopNavigationRoutes.HOME.route) {
            DesktopHomeScreen()
        }
        
        composable(DesktopNavigationRoutes.CHAT.route) {
            DesktopChatScreen()
        }
        
        composable(DesktopNavigationRoutes.NOTIFICATION.route) {
            DesktopNotificationScreen()
        }
        
        composable(DesktopNavigationRoutes.PROFILE.route) {
            DesktopProfileScreen()
        }
        
        composable(DesktopNavigationRoutes.LOGIN.route) {
            DesktopLoginScreen()
        }
    }
}