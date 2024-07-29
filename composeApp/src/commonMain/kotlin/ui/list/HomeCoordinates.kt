package ui.list

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import di.injectedViewModel
import libs.Coordinates
import libs.ViewModelScreen
import navigation.auth.AuthenticatedDestination
import navigation.destinations.LoggedInDestinations
import navigation.auth.screen

object HomeCoordinates : Coordinates {
  fun AuthenticatedDestination.myLists() = screen<LoggedInDestinations.Home> {
    ViewModelScreen(it, injectedViewModel<HomeViewModel>()) {
      var tripTemplateName by remember { mutableStateOf("") }
      TextField(
        value = tripTemplateName,
        onValueChange = { tripTemplateName = it },
        label = { Text("Trip Template Name") }
      )

      Button(onClick = { dispatch(HomeEvents.AddTripTemplate(tripTemplateName)) }) {
        Text("Add")
      }
    }
  }
}