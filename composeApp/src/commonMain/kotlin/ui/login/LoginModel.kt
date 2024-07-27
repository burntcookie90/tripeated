package ui.login

import models.ui.UiUser

data class LoginModel(
  val loginStatus: Boolean,
  val currentUser: UiUser = UiUser.LoggedOut
)
