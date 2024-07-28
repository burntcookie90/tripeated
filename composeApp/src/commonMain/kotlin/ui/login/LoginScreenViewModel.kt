package ui.login

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class LoginScreenViewModel(
  presenter: LoginScreenPresenter
) : MoleculeViewModel<LoginScreenModel, LoginScreenEvents>(
  initialState = LoginScreenModel(loginStatus = false),
  presenter = { lastModel, events -> presenter.present(lastModel, events) }
)