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
import models.database.DbTripTemplate
import service.AuthService
import service.TripTemplateService

@Inject
class HomePresenter(
  private val authService: AuthService,
  private val tripTemplateService: TripTemplateService,
) : Presenter<HomeModel, HomeEvents> {
  @Composable
  override fun present(lastModel: HomeModel, events: Flow<HomeEvents>): HomeModel {
    var model by remember { mutableStateOf(lastModel) }

    ConsumeEvents(events) { event ->
      when (event) {
        is HomeEvents.Logout -> {
          launch {
            authService.logout()
          }
        }

        is HomeEvents.AddTripTemplate -> {
          launch {
            tripTemplateService.addTripTemplate(
              DbTripTemplate(name = event.name)
            )
          }
        }
      }
    }

    return model
  }
}