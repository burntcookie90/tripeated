package models.database

import kotlinx.serialization.Serializable
import models.ListIdentifier

@Serializable
data class DbList(
  val id: ListIdentifier,
  val name: String,
  val items: List<DbItem> = emptyList()
)
