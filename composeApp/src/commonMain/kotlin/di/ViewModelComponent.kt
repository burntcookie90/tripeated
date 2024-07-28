package di

import androidx.lifecycle.ViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import navigation.UserLoggedInViewModel
import ui.login.LoginViewModel
import kotlin.reflect.KClass

typealias vmFactory<VM> = () -> VM

@Component
@ViewModelScope
abstract class ViewModelComponent(
) {
  protected abstract val factories: Map<KClass<out ViewModel>, vmFactory<*>>
  abstract val viewModelFactory: ViewModelFactory

  @Provides
  @IntoMap
  protected fun login(factory: () -> LoginViewModel): Pair<KClass<out ViewModel>, vmFactory<*>> =
    LoginViewModel::class to factory

  @Provides
  @IntoMap
  protected fun userLoggedIn(factory: () -> UserLoggedInViewModel): Pair<KClass<out ViewModel>, vmFactory<*>> =
    UserLoggedInViewModel::class to factory
}
