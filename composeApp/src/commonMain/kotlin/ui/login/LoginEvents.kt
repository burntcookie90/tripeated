package ui.login

sealed interface LoginEvents {
  data class Login(val email: String, val password: String) : LoginEvents
}