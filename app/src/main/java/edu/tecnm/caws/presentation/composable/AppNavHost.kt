package edu.tecnm.caws.presentation.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.tecnm.caws.presentation.screen.HomeScreen
import edu.tecnm.caws.presentation.screen.ListCourseScreen
import edu.tecnm.caws.presentation.screen.LoginScreen
import edu.tecnm.caws.presentation.screen.UserRegisterScreen
import edu.tecnm.caws.presentation.ui.NavigationItem

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, // The modifier to be applied to the layout
    navController: NavHostController, // The navController for this host
    startDestination: String = NavigationItem.UserLogin.route // Start route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.UserLogin.route // Route for the destination
        ) {
           LoginScreen(navController)
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Home.route // Route for the destination
        ) {
            HomeScreen(navController) // Composable for the destination
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.UserRegister.route // Route for the destination
        ) {
            UserRegisterScreen(navController) // Composable for the destination
        }
        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.CourseList.route // Route for the destination
        ) {
            ListCourseScreen(navController) // Composable for the destination
        }
    }
}