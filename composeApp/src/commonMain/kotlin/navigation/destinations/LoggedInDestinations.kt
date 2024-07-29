package navigation.destinations

import kotlinx.serialization.Serializable

interface LoggedInDestinations : TripeatedDestination {
  @Serializable
  object Home: LoggedInDestinations

  @Serializable
  object Profile : LoggedInDestinations

  @Serializable
  object Settings : LoggedInDestinations
}