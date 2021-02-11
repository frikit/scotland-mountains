package org.github.frikit.services

import org.github.frikit.BaseTestClass
import org.github.frikit.models.database.MountainTopType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class FilterServiceByHillTypeTest(
    @Autowired private val dataBaseService: DataBaseService,
    @Autowired private val filterService: FilterService
) : BaseTestClass() {


    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testFilterAllEither() {
        val actual = filterService.filterDataByHillType(dataBaseService.findAll(), null)

        assertEquals(509, actual.size)
    }

    @Test
    fun testFilterAllEither2() {
        val actual = filterService.filterDataByHillType(dataBaseService.findAll(), MountainTopType.EITHER)

        assertEquals(0, actual.size)
    }

    @Test
    fun testFilterAllTOP() {
        val actual = filterService.filterDataByHillType(dataBaseService.findAll(), MountainTopType.TOP)

        assertEquals(227, actual.size)
    }

    @Test
    fun testFilterMUN() {
        val actual = filterService.filterDataByHillType(dataBaseService.findAll(), MountainTopType.MUN)

        assertEquals(282, actual.size)
    }
}
