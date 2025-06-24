package com.unmsm.bustrack.data.repository

import com.unmsm.bustrack.data.model.BusStop

class NetworkBusStopRepository : BusStopRepository {
    override suspend fun getBusStops(): List<BusStop> {
        TODO("Not yet implemented")
    }
}