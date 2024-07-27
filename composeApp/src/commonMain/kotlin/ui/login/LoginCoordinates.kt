package ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

  override val route: String = LoggedOutDestinations.Login.route

  fun NavGraphBuilder.login(
    loginScreenComponent: LoginScreenComponent,
  ) {
    composable(LoggedOutDestinations.Login.route) {
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
    println(model)

    Column(modifier = Modifier.fillMaxSize()) {
      Text("login!")
      Button(onClick = { /*TODO*/ }) {
        Text("Login")
      }
    }
  }
}