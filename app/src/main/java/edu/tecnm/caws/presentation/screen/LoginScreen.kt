package edu.tecnm.caws.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import edu.tecnm.caws.presentation.viewmodel.LoginEvent
import edu.tecnm.caws.presentation.viewmodel.LoginViewModel


@Composable
fun LoginScreen(navController: NavController,
                viewModel:LoginViewModel = hiltViewModel() ) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = state.value.email,
            keyboardActions = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Next)
            },
            onValueChange = {  viewModel.onEvent(LoginEvent.EmailChanged(it)) },
            label = { Text("Email")
                 }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.value.password,
            keyboardActions = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Next)
            },
            onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
            label = {
                Text("Password")
                    },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.onEvent(LoginEvent.LoginClicked) })
        {
            Text("Login")
        }
        if (state.value.error.isNotBlank()) {
            Text(text = state.value.error, color = Color.Red)
        }
        if (state.value.success){
            navController.navigate("UserRegister") {
                popUpTo("UserLogin") { inclusive = true }
            }
        }
    }
}