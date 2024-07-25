package navigation

interface LoggedInDestinations : TripeatedDestination {
  object MyLists: LoggedInDestinations {
    override val route = "lists"
  }

  object Profile : LoggedInDestinations {
    override val route = "profile"
  }

  object Settings : LoggedInDestinations {
    override val route = "settings"
  }
}