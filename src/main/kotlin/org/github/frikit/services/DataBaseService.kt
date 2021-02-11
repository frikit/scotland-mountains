package org.github.frikit.services

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.github.frikit.models.database.Mountain
import org.github.frikit.parser.Parser
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.InputStream
import java.io.Reader

@Service
class DataBaseService {

    private val log = LoggerFactory.getLogger(DataBaseService::class.java)

    private val dataSet = mutableListOf<Mountain>()


    fun loadDatabase(streamToFile: Reader?, parser: Parser) {
        if (streamToFile == null || streamToFile == InputStream.nullInputStream()) {
            log.error("Can't load data in database as stream is null!")
            throw RuntimeException("Can't load data in database as stream is null!")
        }

        val lines = csvReader().readAll(streamToFile.readText())

        val mountains = parser.parse(lines)

        dataSet.clear()
        dataSet.addAll(mountains)

        log.info("Load in database ${dataSet.size} entries!")
    }

    fun findAll(): List<Mountain> {
        return dataSet
    }
}
