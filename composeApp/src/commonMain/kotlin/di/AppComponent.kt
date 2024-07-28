package di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Scope
import service.AuthService
import service.UserService

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class AppScope

@AppScope
@Component
abstract class AppComponent() : ServiceComponent {
  companion object
}

interface ServiceComponent {
  val authService: AuthService

  val userService: UserService
}