package org.github.frikit.config

import org.github.frikit.BaseTestClass
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class ApplicationConfigTest(
    @Autowired private val config: ApplicationConfig
) : BaseTestClass() {

    @Test
    fun testFileNameIsInConfig() {
        assertNotNull(config.filename)
        assertNotEquals("", config.filename.trim())
    }
}
