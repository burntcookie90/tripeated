package ui.list

import androidx.compose.material.Button
import androidx.compose.material.Text
import di.ViewModelScope
import di.injectedViewModel
import libs.Coordinates
import libs.ViewModelScreen
import navigation.auth.AuthenticatedDestination
import navigation.destinations.LoggedInDestinations
import navigation.auth.screen

object MyListsCoordinates : Coordinates {
  fun AuthenticatedDestination.myLists() = screen<LoggedInDestinations.MyLists> {
    ViewModelScreen(it, injectedViewModel<MyListsViewModel>()) {
      Text("My Lists")

      Button(onClick = { dispatch(MyListsEvent.Logout) }) {
        Text("Logout")
      }
    }
  }
}