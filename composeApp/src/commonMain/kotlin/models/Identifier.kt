package models

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

interface Identifier

@Serializable
@JvmInline
value class ItemIdentifier(val value: String): Identifier

@Serializable
@JvmInline
value class ListIdentifier(val value: String): Identifier

@Serializable
@JvmInline
value class TripIdentifier(val value: String): Identifier

@Serializable
@JvmInline
value class TripTempalteIdentifier(val value: String): Identifier
