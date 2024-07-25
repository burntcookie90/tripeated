package ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import libs.Presenter
import me.tatarka.inject.annotations.Inject

@Inject
class LoginPresenter : Presenter<LoginModel, LoginEvents>{
  @Composable
  override fun present(lastModel: LoginModel, events: Flow<LoginEvents>): LoginModel {
    var model by remember { mutableStateOf(lastModel) }

    return model
  }
}