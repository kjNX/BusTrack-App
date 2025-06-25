package com.unmsm.bustrack.data

import com.unmsm.bustrack.data.repository.MapRepository
import com.unmsm.bustrack.data.repository.FakeMapRepository

interface AppContainer {
    val repository: MapRepository
}

class DefaultAppContainer : AppContainer {
    override val repository: MapRepository
        get() = FakeMapRepository()
}