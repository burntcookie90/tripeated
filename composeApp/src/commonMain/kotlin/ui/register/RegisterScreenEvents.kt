package ui.register

sealed interface RegisterScreenEvents {
  data class Register(
    val email: String,
    val password: String,
    val repeatedPassword: String,
  ) : RegisterScreenEvents
}