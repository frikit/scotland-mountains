package org.github.frikit.models

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
    val post: Post,
    val comments: String
)
