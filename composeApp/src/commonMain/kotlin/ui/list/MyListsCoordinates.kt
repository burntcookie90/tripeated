package ui.list

import androidx.compose.material.Text
import libs.Coordinates
import navigation.AuthenticatedDestination
import navigation.LoggedInDestinations
import navigation.screen

object MyListsCoordinates : Coordinates {
  fun AuthenticatedDestination.myLists() = screen<LoggedInDestinations.MyLists> {
    Text("hi")
  }
}