package com.unmsm.bustrack.data.repository

import com.unmsm.bustrack.data.model.BusStop

interface BusStopRepository {
    suspend fun getBusStops(): List<BusStop>
}