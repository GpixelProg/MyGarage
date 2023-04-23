package it.revolution.mygarage.data.api

import kotlinx.serialization.Serializable

@Serializable
data class VehicleType(val name: String, val value: Int)