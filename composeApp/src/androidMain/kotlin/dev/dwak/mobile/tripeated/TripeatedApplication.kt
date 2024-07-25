package dev.dwak.mobile.tripeated

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class TripeatedApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Firebase.initialize(this)
  }
}