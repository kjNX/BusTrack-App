package com.unmsm.bustrack.data.repository

import com.unmsm.bustrack.data.model.MapData

interface MapRepository {
//    suspend fun getBusStops(): List<BusStop>
//    suspend fun getBuses(): List<Bus>
//    suspend fun getRoutes(): List<Route>
    suspend fun getMapData(): MapData
    suspend fun getTime(busStopId: Int, routeId: Int) : Float
}