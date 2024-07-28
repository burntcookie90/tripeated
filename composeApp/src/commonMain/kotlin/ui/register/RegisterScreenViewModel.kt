package ui.register

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterScreenViewModel(
  private val presenter: RegisterScreenPresenter,
) : MoleculeViewModel<RegisterScreenModel, RegisterScreenEvents>(
  initialState = RegisterScreenModel(),
  presenter = { lastModel, events -> presenter.present(lastModel, events) }
)