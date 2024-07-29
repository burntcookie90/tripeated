package models.database

import kotlinx.serialization.Serializable
import models.TripIdentifier
import models.TripTempalteIdentifier

@Serializable
data class DbTripTemplate(
  val id: TripTempalteIdentifier? = null,
  val name: String,
  val lists: List<DbList> = emptyList()
)
