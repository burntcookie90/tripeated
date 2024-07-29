package di

import androidx.lifecycle.ViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope
import navigation.auth.UserLoggedInViewModel
import ui.list.HomeViewModel
import ui.login.LoginScreenViewModel
import ui.register.RegisterScreenViewModel
import kotlin.reflect.KClass

typealias VmFactory<VM> = () -> VM

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ViewModelScope()

@Component
@ViewModelScope
abstract class ViewModelComponent(
  @Component val parent: AppComponent
) {
  protected abstract val factories: Map<KClass<out ViewModel>, VmFactory<*>>
  abstract val viewModelFactory: ViewModelFactory

  @Provides
  @IntoMap
  protected fun login(factory: () -> LoginScreenViewModel): Pair<KClass<out ViewModel>, VmFactory<*>> =
    LoginScreenViewModel::class to factory

  @Provides
  @IntoMap
  protected fun userLoggedIn(factory: () -> UserLoggedInViewModel): Pair<KClass<out ViewModel>, VmFactory<*>> =
    UserLoggedInViewModel::class to factory

  @Provides
  @IntoMap
  protected fun register(factory: () -> RegisterScreenViewModel): Pair<KClass<out ViewModel>, VmFactory<*>> =
    RegisterScreenViewModel::class to factory

  @Provides
  @IntoMap
  protected fun myLists(factory: () -> HomeViewModel): Pair<KClass<out ViewModel>, VmFactory<*>> =
    HomeViewModel::class to factory

  companion object
}
