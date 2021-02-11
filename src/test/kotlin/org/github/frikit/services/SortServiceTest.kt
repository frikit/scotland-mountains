package org.github.frikit.services

import org.github.frikit.BaseTestClass
import org.github.frikit.models.Sort
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired

internal class SortServiceTest(
    @Autowired private val dataBaseService: DataBaseService,
    @Autowired private val sortService: SortService
) : BaseTestClass() {

    @Test
    fun testSortNoCriteria() {
        val input = dataBaseService.findAll()
        val expected = input

        val actual = sortService.sortBy(input, emptyList())

        assertTrue(expected.zip(actual).all { it.first == it.second })
    }

    @Test
    fun testSortByUnknownCriteria() {
        val input = dataBaseService.findAll()
        val expected = input

        val actual = sortService.sortBy(input, listOf(Pair("unknown", Sort.ASC)))

        assertTrue(expected.zip(actual).all { it.first == it.second })
    }

    @ParameterizedTest
    @ValueSource(strings = ["height.meters", "name"])
    fun testSortByKnownCriteriaASC(field: String) {
        val input = dataBaseService.findAll()
        val expected = if (field == "height.meters")
            input.sortedBy { it.height.metres }
        else
            input.sortedBy { it.name }

        val actual = sortService.sortBy(input, listOf(Pair(field, Sort.ASC)))

        assertTrue(expected.zip(actual).all { it.first == it.second })
    }

    @ParameterizedTest
    @ValueSource(strings = ["height.meters", "name"])
    fun testSortByKnownCriteriaDESC(field: String) {
        val input = dataBaseService.findAll()
        val expected = if (field == "height.meters")
            input.sortedBy { it.height.metres }.reversed()
        else
            input.sortedBy { it.name }.reversed()

        val actual = sortService.sortBy(input, listOf(Pair(field, Sort.DESC)))

        assertTrue(expected.zip(actual).all { it.first == it.second })
    }

    @ParameterizedTest
    @ValueSource(strings = ["height.meters", "name"])
    fun testSortByKnownCriteriaNONEasSortOrder(field: String) {
        val input = dataBaseService.findAll()
        val expected = input

        val actual = sortService.sortBy(input, listOf(Pair(field, Sort.NONE)))

        assertTrue(expected.zip(actual).all { it.first == it.second })
    }

}
