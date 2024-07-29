package models.database

import kotlinx.serialization.Serializable
import models.ItemIdentifier

@Serializable
data class DbItem(
  val id: ItemIdentifier,
  val name: String
)
