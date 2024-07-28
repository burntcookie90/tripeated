package navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
object TripeatedRoot: TripeatedDestination

interface RootDestination : TripeatedDestination {
  @Serializable
  object LoggedIn : RootDestination

  @Serializable
  object LoggedOut : RootDestination
}