package org.github.frikit.models.response

import org.github.frikit.models.database.MountainTopType

data class MountainShortInfo(
    val name: String,
    val heightMeters: Double,
    //post 1997
    val hillCategory: MountainTopType,
    val gridRef: String
)
