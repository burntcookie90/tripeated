package navigation

import kotlinx.serialization.Serializable

interface LoggedOutDestinations : TripeatedDestination {
  @Serializable
  data object Login : LoggedOutDestinations

  @Serializable
  object Register : LoggedOutDestinations

  @Serializable
  object Welcome : LoggedOutDestinations
}