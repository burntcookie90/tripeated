package ui.list

import androidx.compose.material.Text
import libs.Coordinates
import navigation.auth.AuthenticatedDestination
import navigation.destinations.LoggedInDestinations
import navigation.auth.screen

object MyListsCoordinates : Coordinates {
  fun AuthenticatedDestination.myLists() = screen<LoggedInDestinations.MyLists> {
    Text("hi")
  }
}