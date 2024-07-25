package navigation

interface LoggedOutDestinations : TripeatedDestination {
  object Login : LoggedOutDestinations {
    override val route = "login"
  }

  object Register : LoggedOutDestinations {
    override val route = "register"
  }

  object Welcome : LoggedOutDestinations {
    override val route = "welcome"
  }
}