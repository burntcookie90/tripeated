package navigation

import kotlinx.serialization.Serializable

interface LoggedInDestinations : TripeatedDestination {
  @Serializable
  object MyLists: LoggedInDestinations

  @Serializable
  object Profile : LoggedInDestinations

  @Serializable
  object Settings : LoggedInDestinations
}