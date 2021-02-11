package org.github.frikit.controllers

import org.github.frikit.models.ModelsMappers.Companion.mapToMinimalInfo
import org.github.frikit.models.Sort
import org.github.frikit.models.database.MountainTopType
import org.github.frikit.models.response.MountainShortInfo
import org.github.frikit.services.DataBaseService
import org.github.frikit.services.FilterService
import org.github.frikit.services.FilterService.Companion.filterDataByHillHeight
import org.github.frikit.services.FilterService.Companion.filterDataByHillType
import org.github.frikit.services.SortService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
class SearchController(
    @Autowired private val dataBaseService: DataBaseService,
    @Autowired private val sortService: SortService
) {

    private val log = LoggerFactory.getLogger(SearchController::class.java)

    @GetMapping("/search/byHill")
    fun searchByHill(
        @RequestParam("type", required = false, defaultValue = "") type: String?,
        @RequestParam("limit", required = false, defaultValue = Integer.MAX_VALUE.toString()) limit: Int,
        @RequestParam("minHeight", required = false, defaultValue = "0") minHeight: Double,
        @RequestParam("maxHeight", required = false, defaultValue = Double.MAX_VALUE.toString()) maxHeight: Double,
        @RequestParam params: Map<String, String>
    ): List<MountainShortInfo> {
        //filter
        val filterBy = when (type?.trim()?.toUpperCase()) {
            "Munro".toUpperCase() -> MountainTopType.MUN
            "Munro Top".toUpperCase() -> MountainTopType.TOP
            else -> MountainTopType.EITHER
        }

        val filteredData = dataBaseService.findAll()
            .filterDataByHillType(filterBy)
            .filterDataByHillHeight(minHeight, maxHeight)

        //sort
        //find all sort
        val sortFilters = mutableListOf<Pair<String, Sort>>()
        var atLeastOneSortFilter = params.entries.map { it.key }.find { it.startsWith("sort_") } != null

        val index = AtomicInteger(1)
        while (atLeastOneSortFilter) {
            val value = params["sort_field${index.get()}"]
            val valueSort = params["sort_dir${index.get()}"]

            if (value == null && valueSort == null) {
                atLeastOneSortFilter = false
            } else if (value != null && valueSort == null) {
                sortFilters.add(Pair(value, Sort.NONE))
            } else if (value != null && valueSort != null) {
                val sort = when (valueSort.trim().toUpperCase()) {
                    "ASC" -> Sort.ASC
                    "DESC" -> Sort.DESC
                    else -> Sort.NONE
                }
                sortFilters.add(Pair(value, sort))
            } else {
                log.error("Should not happen that case! :)")
            }

            index.incrementAndGet()
        }

        val sortedData = sortService.sortBy(filteredData, sortFilters)

        return sortedData
            .take(limit)
            .mapToMinimalInfo()
    }
}

