package di

import me.tatarka.inject.annotations.Component
import service.ServiceComponent

@AppScope
@Component
abstract class AppComponent(
  val viewModelComponent: ViewModelComponent
) : ServiceComponent {


  companion object
}