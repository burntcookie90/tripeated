package models.database

import kotlinx.serialization.Serializable

@Serializable
data class DbItem(
  val id: String,
  val name: String
)
