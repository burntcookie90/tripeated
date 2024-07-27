package ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import libs.Coordinates
import libs.ViewModelScreen
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Inject
import navigation.LoggedOutDestinations
import ui.ScreenComponent
import ui.viewModel

object LoginCoordinates: Coordinates {

  interface LoginScreenComponent: ScreenComponent<LoginViewModel> {
    override val viewModelFactory: () -> LoginViewModel
  }

  fun NavGraphBuilder.login(
    loginScreenComponent: LoginScreenComponent,
  ) {
    composable<LoggedOutDestinations.Login> {
      ViewModelScreen(loginScreenComponent.viewModel()) {->
        LoginScreen(model, dispatch)
      }
    }
  }

  @Inject
  @Composable
  fun LoginScreen(
    model: LoginModel,
    dispatch: (LoginEvents) -> Unit
  ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
      Button(onClick = { dispatch(LoginEvents.Login(email, password)) }) {
        Text("Login")
      }
    }
  }
}