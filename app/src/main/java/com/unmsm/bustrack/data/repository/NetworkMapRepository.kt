package com.unmsm.bustrack.data.repository

import com.unmsm.bustrack.data.model.MapData

class NetworkMapRepository : MapRepository {
    override suspend fun getMapData(): MapData {
        TODO("Not yet implemented")
    }

    override suspend fun getTime(busStopId: Int, routeId: Int): Float {
        TODO("Not yet implemented")
    }
}