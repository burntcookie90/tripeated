package di

import me.tatarka.inject.annotations.Component
import service.ServiceComponent
import ui.login.LoginCoordinates

@AppScope
@Component
abstract class AppComponent(
) : LoginCoordinates.LoginScreenComponent,
  ServiceComponent {
  companion object
}