package org.github.frikit.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ApplicationConfig {

    @Value("\${filename}")
    lateinit var filename: String
}
