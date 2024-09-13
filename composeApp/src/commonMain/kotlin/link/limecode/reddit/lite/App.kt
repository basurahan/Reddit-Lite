package link.limecode.reddit.lite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Root()
    }
}

@Composable
fun Root() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text("Home")
                    }
                )

                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text("Chats")
                    }
                )

                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text("Create")
                    }
                )

                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text("Notification")
                    }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text("Profile")
                    }
                )
            }
        }
    ) {
        AppNavigation(
            navController = navController,
            modifier = Modifier.fillMaxSize()
        )
    }
}