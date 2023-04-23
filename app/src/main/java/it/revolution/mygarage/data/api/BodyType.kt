package it.revolution.mygarage.data.api

import kotlinx.serialization.Serializable

@Serializable
data class BodyType(val name: String, val value: Int, val parentId: Int)
