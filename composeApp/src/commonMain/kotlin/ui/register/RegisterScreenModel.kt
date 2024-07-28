package ui.register

import models.ui.UiUser

data class RegisterScreenModel(
  val currentUser: UiUser = UiUser.LoggedOut
)
