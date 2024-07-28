package ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import libs.ConsumeEvents
import libs.Presenter
import me.tatarka.inject.annotations.Inject
import service.AuthService

@Inject
class MyListsPresenter(
  private val authService: AuthService
) : Presenter<MyListsModel, MyListsEvent> {
  @Composable
  override fun present(lastModel: MyListsModel, events: Flow<MyListsEvent>): MyListsModel {
    var model by remember { mutableStateOf(lastModel) }

    ConsumeEvents(events) { event ->
      when (event) {
        is MyListsEvent.Logout -> {
          launch {
            authService.logout()
          }
        }
      }
    }

    return model
  }
}