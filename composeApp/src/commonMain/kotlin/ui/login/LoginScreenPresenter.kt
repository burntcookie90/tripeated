package ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
class LoginScreenPresenter(
  private val authService: AuthService
) : Presenter<LoginScreenModel, LoginScreenEvents>{
  @Composable
  override fun present(lastModel: LoginScreenModel, events: Flow<LoginScreenEvents>): LoginScreenModel {
    var model by remember { mutableStateOf(lastModel) }

    LaunchedEffect(Unit) {
      authService.authState().collect { user ->
        model = model.copy(currentUser = user)
      }
    }

    ConsumeEvents(events) { event ->
      when (event) {
        is LoginScreenEvents.Login -> {
          launch {
            authService.login(event.email, event.password)
          }
        }
      }
    }

    return model
  }
}