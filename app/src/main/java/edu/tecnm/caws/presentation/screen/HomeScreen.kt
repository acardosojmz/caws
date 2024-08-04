package edu.tecnm.caws.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import edu.tecnm.caws.R
import edu.tecnm.caws.presentation.viewmodel.HomeEvent
import edu.tecnm.caws.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController,
               viewModel: HomeViewModel = hiltViewModel() ) {
    val user = viewModel.user.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.tlaxiaco),
            contentDescription = "logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome, ${user.value?.email}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onEvent(HomeEvent.LogoutClicked) }) {
            Text("Logout")
        }
    }
}