package service

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import di.AppScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject
import models.ui.UiUser

@AppScope
@Inject
class AuthService {
  private val auth = Firebase.auth

  suspend fun register(email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password)
  }

  suspend fun login(email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
  }

  suspend fun logout() = auth.signOut()

  fun authState(): Flow<UiUser> = auth.authStateChanged.map {
    it?.let { UiUser.LoggedIn(it.uid, it.email ?: "") } ?: UiUser.LoggedOut
  }

}