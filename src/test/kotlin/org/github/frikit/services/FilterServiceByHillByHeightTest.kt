package org.github.frikit.services

import org.github.frikit.BaseTestClass
import org.github.frikit.models.database.MountainTopType
import org.github.frikit.services.FilterService.Companion.filterDataByHillHeight
import org.github.frikit.services.FilterService.Companion.filterDataByHillType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class FilterServiceByHillByHeightTest(
    @Autowired private val dataBaseService: DataBaseService
) : BaseTestClass() {

    @Test
    fun testFilterNoneIncludeAll() {
        val actual = dataBaseService.findAll().filterDataByHillHeight(Double.MIN_VALUE, Double.MAX_VALUE)

        assertEquals(602, actual.size)
    }

    @Test
    fun testFilterBetween2Numbers() {
        val actual = dataBaseService.findAll().filterDataByHillHeight(888.0, 906.0)

        assertEquals(3, actual.size)
    }
}
