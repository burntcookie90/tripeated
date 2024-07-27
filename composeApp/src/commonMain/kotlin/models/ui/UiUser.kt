package models.ui

sealed interface UiUser {
  data class LoggedIn(
    val id: String,
    val email: String
  ): UiUser

  data object LoggedOut : UiUser
}
