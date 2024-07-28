package ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import di.injectedViewModel
import libs.Coordinates
import libs.ViewModelScreen
import navigation.destinations.LoggedOutDestinations

object RegisterScreenCoordinates : Coordinates {
  fun NavGraphBuilder.register() = composable<LoggedOutDestinations.Register> {
    ViewModelScreen(it, injectedViewModel<RegisterScreenViewModel>()) {
      Column(modifier = Modifier.fillMaxSize()) {
        Text("register!")
        var email by remember {
          mutableStateOf("")
        }

        var password by remember {
          mutableStateOf("")
        }

        var shouldSubmitReg by remember {
          mutableStateOf(false)
        }

        TextField(
          value = email,
          onValueChange = { email = it },
          label = { Text("Email") }
        )

        TextField(
          value = password,
          visualTransformation = PasswordVisualTransformation(),
          onValueChange = { password = it },
          label = { Text("Password") }
        )

        Button(onClick = { shouldSubmitReg = true }) {
          Text("Register")
        }
      }
    }
  }
}