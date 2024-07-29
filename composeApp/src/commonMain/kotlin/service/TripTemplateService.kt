package service

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import di.AppScope
import me.tatarka.inject.annotations.Inject
import models.database.DbTripTemplate

@AppScope
@Inject
class TripTemplateService {
  private val db = Firebase.firestore.collection("trip_templates")

  suspend fun addTripTemplate(trip: DbTripTemplate) {
    db.add(trip)
  }
}