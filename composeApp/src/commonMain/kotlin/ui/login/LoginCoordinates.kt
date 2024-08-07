package ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import models.ui.UiUser
import navigation.destinations.LoggedOutDestinations

object LoginCoordinates : Coordinates {

  fun NavGraphBuilder.login(
    onLoginSuccess: () -> Unit
  ) {
    composable<LoggedOutDestinations.Login> {
      ViewModelScreen(
        navBackStackEntry = it,
        viewModel = injectedViewModel<LoginScreenViewModel>()
      ) { ->
        LoginScreen(model, dispatch, onLoginSuccess)
      }
    }
  }

  @Composable
  fun LoginScreen(
    model: LoginScreenModel,
    dispatch: (LoginScreenEvents) -> Unit,
    onLoginSuccess: () -> Unit
  ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(model.currentUser) {
      if (model.currentUser is UiUser.LoggedIn) {
        onLoginSuccess()
      }
    }

    Column(modifier = Modifier.fillMaxSize()) {
      TextField(
        value = email,
        onValueChange = { email = it },
      )

      TextField(
        value = password,
        onValueChange = { password = it },
        visualTransformation = PasswordVisualTransformation()
      )
      Button(onClick = { dispatch(LoginScreenEvents.Login(email, password)) }) {
        Text("Login")
      }
    }
  }
}