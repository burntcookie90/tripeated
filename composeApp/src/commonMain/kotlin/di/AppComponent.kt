package di

import me.tatarka.inject.annotations.Component
import navigation.UserLoggedInViewModelComponent
import service.ServiceComponent
import ui.login.LoginCoordinates

@AppScope
@Component
abstract class AppComponent(
  val viewModelComponent: ViewModelComponent
) : UserLoggedInViewModelComponent,
  ServiceComponent {


  companion object
}