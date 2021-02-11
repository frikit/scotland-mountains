package org.github.frikit.controllers

import org.github.frikit.models.ModelsMappers.Companion.mapToMinimalInfo
import org.github.frikit.models.database.MountainTopType
import org.github.frikit.models.response.MountainShortInfo
import org.github.frikit.services.DataBaseService
import org.github.frikit.services.FilterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    @Autowired private val dataBaseService: DataBaseService,
    @Autowired private val filterService: FilterService
) {

    @GetMapping("/search/byHill")
    fun searchByHill(
        @RequestParam("type", required = false, defaultValue = "") type: String?,
        @RequestParam("limit", required = false, defaultValue = Integer.MAX_VALUE.toString()) limit: Int
    ): List<MountainShortInfo> {
        val filterBy = when(type) {
            "Munro" -> MountainTopType.MUN
            "Munro Top" -> MountainTopType.TOP
            else -> MountainTopType.EITHER
        }

        return filterService
            .filterDataByHillType(dataBaseService.findAll(), filterBy)
            .take(limit)
            .mapToMinimalInfo()
    }
}

