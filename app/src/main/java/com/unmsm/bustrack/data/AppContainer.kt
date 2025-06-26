package com.unmsm.bustrack.data

import com.unmsm.bustrack.data.repository.FakeMapRepository
import com.unmsm.bustrack.data.repository.MapRepository

interface AppContainer {
    val repository: MapRepository
}

class DefaultAppContainer : AppContainer {
    /*private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BusTrackApiService by lazy {
        retrofit.create(BusTrackApiService::class.java)
    }

    override val marsPhotosRepository: MapRepository by lazy {
        NetworkMapRepository(retrofitService)
    }*/

    override val repository: MapRepository
        get() = FakeMapRepository()
}