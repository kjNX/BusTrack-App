package com.unmsm.bustrack.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NextBus(
    val id: Int,
    val name: String,
    val time: Float // in seconds
) {
    val formattedTime: String
        get() {
            val minutes = (time / 60).toInt()
            val seconds = (time % 60).toInt()
            return "${if(minutes > 0) "${minutes}m" else ""}${seconds}s"
        }
}
