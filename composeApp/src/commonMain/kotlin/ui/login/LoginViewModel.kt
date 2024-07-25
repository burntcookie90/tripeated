package ui.login

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class LoginViewModel(
  presenter: LoginPresenter
) : MoleculeViewModel<LoginModel, LoginEvents>(
  initialState = LoginModel(loginStatus = false),
  presenter = { lastModel, events -> presenter.present(lastModel, events) }
)