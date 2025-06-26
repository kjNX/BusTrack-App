package com.unmsm.bustrack.data.network

import com.unmsm.bustrack.data.model.BusStop
import com.unmsm.bustrack.data.model.NextBus
import retrofit2.http.GET
import retrofit2.http.Path

interface BusTrackApiService {
    @GET("paradero")
    suspend fun getBusStops() : List<BusStop>

    @GET("paradero/{id}/rutas")
    suspend fun getTimes(@Path("id") busId: Int) : List<NextBus>
}
