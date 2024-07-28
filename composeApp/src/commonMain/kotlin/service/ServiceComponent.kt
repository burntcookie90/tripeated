package service

import di.AppScope

interface ServiceComponent {
  val authService: AuthService
}