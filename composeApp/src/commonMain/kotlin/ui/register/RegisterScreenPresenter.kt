package ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import libs.ConsumeEvents
import libs.Presenter
import me.tatarka.inject.annotations.Inject
import service.AuthService

@Inject
class RegisterScreenPresenter(
  private val authService: AuthService
) : Presenter<RegisterScreenModel, RegisterScreenEvents> {

  @Composable
  override fun present(
    lastModel: RegisterScreenModel,
    events: Flow<RegisterScreenEvents>
  ): RegisterScreenModel {
    var model by remember { mutableStateOf(lastModel) }

    LaunchedEffect(Unit) {
      authService.authState().collect { user ->
        model = model.copy(currentUser = user)
      }
    }

    ConsumeEvents(events) { event ->
      when (event) {
        is RegisterScreenEvents.Register -> {
          authService.register(
            email = event.email,
            password = event.password
          )
        }
      }
    }

    return model
  }
}