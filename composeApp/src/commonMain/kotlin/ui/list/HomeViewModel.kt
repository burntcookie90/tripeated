package ui.list

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class HomeViewModel(
  presenter: HomePresenter
) : MoleculeViewModel<HomeModel, HomeEvents>(
  initialState = HomeModel(),
  presenter = { model, events -> presenter.present(model, events) }
)