package edu.tecnm.caws.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import edu.tecnm.caws.presentation.viewmodel.RegisterEvent
import edu.tecnm.caws.presentation.viewmodel.UserViewModel

@Composable
fun UserRegisterScreen(navController: NavController,
                       userViewModel: UserViewModel = hiltViewModel()) {

    val state = userViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = state.value.email,
            onValueChange = {  userViewModel.onEvent(RegisterEvent.EmailChanged(it))  },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.value.password,
            onValueChange = { userViewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.value.confirmPassword,
            onValueChange = { userViewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it))},
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (state.value.email.trim()!="" && state.value.password.trim()!="" &&
                state.value.password ==  state.value.confirmPassword
                ) {
                userViewModel.register(state.value.email, state.value.password)
            } 
        }) {
            Text("Register")
        }
        if (state.value.success) {
            Snackbar(
                action = {
                    TextButton(onClick = {userViewModel.onEvent(RegisterEvent.EmptyControls) }) {
                        Text("Done")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Registration successful!")
            }
        }
    }



}

