package org.github.frikit.services

import org.github.frikit.models.Sort
import org.github.frikit.models.database.Mountain
import org.springframework.stereotype.Service

@Service
class SortService {

    fun sortBy(
        input: List<Mountain>,
        sortByFields: List<Pair<String, Sort>>
    ): List<Mountain> {
        if (sortByFields.isEmpty()) return input

        val res = input.toMutableList()

        sortByFields.forEach { sortFilter ->
            val field = sortFilter.first
            val order = sortFilter.second

            if (order != Sort.NONE) {
                when (field) {
                    "height.meters" -> res.sortBy { it.height.metres }
                    "name" -> res.sortBy { it.name }
                    else -> res
                }

                if (order == Sort.DESC) {
                    res.reverse()
                }
            }
        }

        return res
    }
}
