package navigation.auth

data class UserLoggedInModel(
  val state: AuthState = AuthState.LOADING,
)

enum class AuthState {
  LOADING,
  LOGGED_IN,
  LOGGED_OUT
}
