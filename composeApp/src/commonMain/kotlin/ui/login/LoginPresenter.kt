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
class LoginPresenter(
  private val authService: AuthService
) : Presenter<LoginModel, LoginEvents>{
  @Composable
  override fun present(lastModel: LoginModel, events: Flow<LoginEvents>): LoginModel {
    var model by remember { mutableStateOf(lastModel) }

    LaunchedEffect(Unit) {
      authService.authState().collect { user ->
        model = model.copy(currentUser = user)
      }
    }

    ConsumeEvents(events) { event ->
      when (event) {
        is LoginEvents.Login -> {
          launch {
            authService.login(event.email, event.password)
          }
        }
      }
    }

    return model
  }
}