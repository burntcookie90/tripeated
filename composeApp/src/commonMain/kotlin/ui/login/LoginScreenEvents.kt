package ui.login

sealed interface LoginScreenEvents {
  data class Login(val email: String, val password: String) : LoginScreenEvents
}