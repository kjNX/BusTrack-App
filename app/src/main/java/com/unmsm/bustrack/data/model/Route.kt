package com.unmsm.bustrack.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: Int,
    val name: String
)
