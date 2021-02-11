package org.github.frikit.config

import org.github.frikit.BaseTestClass
import org.github.frikit.services.DataBaseService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired

internal class InMemoryDataSetReaderTest(
    @Autowired private val dataBaseService: DataBaseService
) : BaseTestClass() {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testDefaultDataLoadedWithSuccess() {
        assertEquals(602, dataBaseService.findAll().size)
    }

    @Test
    fun testDefaultDataLoadedWithSuccessAndAreUnique() {
        assertEquals(602, dataBaseService.findAll().distinctBy { it.number }.size)
    }
}
