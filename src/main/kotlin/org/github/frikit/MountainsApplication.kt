package org.github.frikit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MountainsApplication

fun main(args: Array<String>) {
    runApplication<MountainsApplication>(*args)
}
