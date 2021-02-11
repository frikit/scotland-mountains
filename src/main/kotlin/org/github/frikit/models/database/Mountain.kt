package org.github.frikit.models.database

data class Mountain(
    val number: Long,
    val doBIHNumber: Long,
    val externalResources: ExternalResources,
    val name: String,
    val section: Section,
    val height: Height,
    val mapScale: MapScale,
    val gridRef: GridRef,
    val coordinate: Coordinate,
    val hillCategory: HillCategory,
    val comments: String
)
