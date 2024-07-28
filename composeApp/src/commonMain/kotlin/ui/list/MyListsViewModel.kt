package ui.list

import libs.MoleculeViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class MyListsViewModel(
  presenter: MyListsPresenter
) : MoleculeViewModel<MyListsModel, MyListsEvent>(
  initialState = MyListsModel(),
  presenter = { model, events -> presenter.present(model, events) }
)