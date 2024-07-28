package navigation

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class UserLoggedInViewModel(presenter: UserLoggedInPresenter) :
  MoleculeViewModel<UserLoggedInModel, UserLoggedInEvents>(
    initialState = UserLoggedInModel(),
    presenter = { model, events -> presenter.present(model, events) }
  )