package org.github.frikit.config

import org.github.frikit.parser.CSVParser
import org.github.frikit.services.DataBaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.io.File

@Component
class InMemoryDataSetReader(
    @Autowired private val dataBaseService: DataBaseService,
    @Autowired private val config: ApplicationConfig
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val filename = config.filename
        val streamToFile = File(".").absoluteFile
            .walkTopDown()
            .find { it.isFile && it.name == filename }?.bufferedReader()

        //TODO inject parser after
        dataBaseService.loadDatabase(streamToFile, CSVParser())
    }

}
