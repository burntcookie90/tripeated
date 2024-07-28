package ui.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import libs.Coordinates
import navigation.destinations.LoggedOutDestinations

object WelcomeScreenCoordinates : Coordinates {
  fun NavGraphBuilder.welcome(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
  ) = composable<LoggedOutDestinations.Welcome> {
    Column(modifier = Modifier.fillMaxSize()) {
      Text("welcome!")
      Button(onClick = {
        navigateToLogin()
      }) {
        Text("Login")
      }

      Button(onClick = {
        navigateToRegister()
      }) {
        Text("Register")
      }
    }
  }
}