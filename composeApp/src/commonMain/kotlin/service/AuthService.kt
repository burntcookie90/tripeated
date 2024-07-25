package service

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import models.ui.UiUser

class AuthService {
  private val auth = Firebase.auth

  suspend fun register(email: String, password: String) : UiUser? {
    val result = auth.createUserWithEmailAndPassword(email, password)

    return result.user?.let { UiUser(it.uid, it.email!!) }
  }
}