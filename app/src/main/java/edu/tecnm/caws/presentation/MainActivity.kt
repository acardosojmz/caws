package edu.tecnm.caws.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.tecnm.caws.presentation.screen.HomeScreen
import edu.tecnm.caws.presentation.screen.LoginScreen
import edu.tecnm.caws.presentation.screen.UserRegisterScreen
import edu.tecnm.caws.presentation.ui.NavigationItem
import edu.tecnm.caws.ui.theme.CawsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CawsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    setContent {
                        CawsApp()
                    }
                }
            }
        }
    }
}

@Composable
fun CawsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("userRegister") { UserRegisterScreen(navController) }
    }
}