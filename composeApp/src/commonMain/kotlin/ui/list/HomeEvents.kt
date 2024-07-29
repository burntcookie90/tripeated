package ui.list

sealed interface HomeEvents {
  data class AddTripTemplate(
    val name: String
  ): HomeEvents

  data object Logout : HomeEvents
}