package ui.login

import models.ui.UiUser

data class LoginScreenModel(
  val loginStatus: Boolean,
  val currentUser: UiUser = UiUser.LoggedOut
)
