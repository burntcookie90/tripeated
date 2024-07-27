package navigation

import kotlinx.serialization.Serializable

interface RootDestination : TripeatedDestination {
  @Serializable
  object LoggedIn : RootDestination

  @Serializable
  object LoggedOut : RootDestination
}