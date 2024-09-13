package link.limecode.reddit.lite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import link.limecode.reddit.lite.ui.pages.home.HomeScreen
import link.limecode.reddit.lite.ui.pages.login.LoginScreen

internal sealed class Screen(val route: String) {
    
    data object Home: Screen(route = "home")
    data object Login: Screen(route = "login")
}

private sealed class RouteBuilder(
    private val route: String
) {
    fun createRoute(root: Screen) = "${root.route}/$route"
    
    data object Home: RouteBuilder("home")
    data object Chats: RouteBuilder("chats")
    data object CreatePost: RouteBuilder("createpost")
    data object Notification: RouteBuilder("notification")
    data object Profile: RouteBuilder("profile")
    
    data object Login: RouteBuilder("login")
}

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        
        navigation(
            route = Screen.Home.route,
            startDestination = RouteBuilder.Home.createRoute(Screen.Home)
        ) {
            addHomeStack()
            addChatsStack()
            addCreatePostStack()
            addNotificationStack()
            addProfileStack()
        }
        
        navigation(
            route = Screen.Login.route,
            startDestination = RouteBuilder.Login.createRoute(Screen.Login)
        ) {
            composable(RouteBuilder.Login.createRoute(Screen.Login)) {
                LoginScreen()
            }
        }
    }
}

private fun NavGraphBuilder.addHomeStack() {
    composable(RouteBuilder.Home.createRoute(Screen.Home)) {
        HomeScreen()
    }
}

private fun NavGraphBuilder.addChatsStack() {
    composable(RouteBuilder.Chats.createRoute(Screen.Home)) {
        
    }
}

private fun NavGraphBuilder.addCreatePostStack() {
    composable(RouteBuilder.CreatePost.createRoute(Screen.Home)) {
        
    }
}

private fun NavGraphBuilder.addNotificationStack() {
    composable(RouteBuilder.Notification.createRoute(Screen.Home)) {
        
    }
}

private fun NavGraphBuilder.addProfileStack() {
    composable(RouteBuilder.Profile.createRoute(Screen.Home)) {
        
    }
}