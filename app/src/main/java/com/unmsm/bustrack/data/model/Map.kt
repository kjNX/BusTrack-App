package com.unmsm.bustrack.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MapData(
    val bus: List<Bus>,
    val busStops: List<BusStop>,
    val routes: List<Route>
)
