package ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import libs.Coordinates
import libs.ViewModelScreen
import navigation.LoggedOutDestinations

object LoginCoordinates: Coordinates {
  override val route: String = LoggedOutDestinations.Login.route

  fun NavGraphBuilder.login() {
    composable(LoggedOutDestinations.Login.route) {
//      canGoBack = true
      Column(modifier = Modifier.fillMaxSize()) {
        Text("login!")
        Button(onClick = { /*TODO*/ }) {
          Text("Login")
        }
      }
    }
  }
}