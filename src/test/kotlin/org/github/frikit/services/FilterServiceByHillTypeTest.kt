package org.github.frikit.services

import org.github.frikit.BaseTestClass
import org.github.frikit.models.database.MountainTopType
import org.github.frikit.services.FilterService.Companion.filterDataByHillType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class FilterServiceByHillTypeTest(
    @Autowired private val dataBaseService: DataBaseService
) : BaseTestClass() {

    @Test
    fun testFilterAllEither() {
        val actual = dataBaseService.findAll().filterDataByHillType(null)

        assertEquals(509, actual.size)
    }

    @Test
    fun testFilterAllEither2() {
        val actual = dataBaseService.findAll().filterDataByHillType(MountainTopType.EITHER)

        assertEquals(0, actual.size)
    }

    @Test
    fun testFilterAllTOP() {
        val actual = dataBaseService.findAll().filterDataByHillType(MountainTopType.TOP)

        assertEquals(227, actual.size)
    }

    @Test
    fun testFilterMUN() {
        val actual = dataBaseService.findAll().filterDataByHillType(MountainTopType.MUN)

        assertEquals(282, actual.size)
    }
}
