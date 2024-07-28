package navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import libs.Presenter
import me.tatarka.inject.annotations.Inject
import models.ui.UiUser
import service.AuthService

@Inject
class UserLoggedInPresenter(private val authService: AuthService) :
  Presenter<UserLoggedInModel, UserLoggedInEvents> {
  @Composable
  override fun present(
    lastModel: UserLoggedInModel,
    events: Flow<UserLoggedInEvents>
  ): UserLoggedInModel {
    var model by remember { mutableStateOf(lastModel) }

    LaunchedEffect(Unit) {
      authService.authState()
        .map {
          when (it) {
            is UiUser.LoggedIn -> AuthState.LOGGED_IN
            is UiUser.LoggedOut -> AuthState.LOGGED_OUT
          }
        }
        .collect {
          model = model.copy(state = it)
        }
    }
    return model
  }
}