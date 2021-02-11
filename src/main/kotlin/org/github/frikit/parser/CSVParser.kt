package org.github.frikit.parser

import org.github.frikit.models.database.*
import org.slf4j.LoggerFactory

class CSVParser : Parser {

    private val log = LoggerFactory.getLogger(CSVParser::class.java)


    /**
     * line.take(line.size - 13).all { it.isNotBlank() } - from 1891 till Comments can be empty other elements cant be
     */
    private fun isValidRow(line: List<String>, headers: List<String>): Boolean {
        return line.size == headers.size && line.take(line.size - 13).all { it.isNotBlank() }
    }

    private fun parseRows(lines: List<List<String>>, headers: List<String>): List<Mountain> {
        if (lines.isEmpty()) return emptyList()
        if (headers.isEmpty()) return emptyList()

        return lines.mapIndexed { idx, line ->
            log.trace("Parse $idx -> $line")
            val splitLine = line
            if (isValidRow(splitLine, headers)) {
                mapLineToObject(splitLine, headers)
            } else {
                log.error("Fail to parse line with index=$idx!")
                null
            }
        }.filterNotNull()
    }

    /**
     * TODO create a class and map header to a index and parse row per this metadata
     */
    private fun mapLineToObject(line: List<String>, headers: List<String>): Mountain {
        val nr = line.get(0).toLong()
        val dobih = line.get(1).toLong()

//        external urls
        val streetMap = line.get(2)
        val geograph = line.get(3)
        val hillBagging = line.get(4)
        val externalResources = ExternalResources(
            streetMapURL = streetMap,
            geographURL = geograph,
            hillBaggingURL = hillBagging
        )

        val name = line.get(5)

        val smc = line.get(6).toInt()
        val rbh = line.get(7)
        val sec = line.get(8).toDouble()
        val section = Section(smcSection = smc, rbhSection = rbh, _section = sec)

//        heights
        val hM = line.get(9).toDouble()
        val hF = line.get(10).toDouble()
        val heights = Height(metres = hM, feet = hF)

        val scale50 = line.get(11).split(" ")
        val scale25 = line.get(12).split(" ")
        val scale = MapScale(scaleOneToFifty = scale50, scaleOneToTwentyFive = scale25)

        val ref = line.get(13)
        val refxy = line.get(14)
        val grid = GridRef(ref = ref, refXY = refxy)

        val x = line.get(15).toLong()
        val y = line.get(16).toLong()
        val coord = Coordinate(x = x, y = y)

        val _1891 = MountainTopType.parse(line.get(17))
        val _1921 = MountainTopType.parse(line.get(18))
        val _1933 = MountainTopType.parse(line.get(19))
        val _1953 = MountainTopType.parse(line.get(20))
        val _1969 = MountainTopType.parse(line.get(21))
        val _1974 = MountainTopType.parse(line.get(22))
        val _1981 = MountainTopType.parse(line.get(23))
        val _1984 = MountainTopType.parse(line.get(24))
        val _1990 = MountainTopType.parse(line.get(25))
        val _1997 = MountainTopType.parse(line.get(26))
        val post1997 = MountainTopType.parse(line.get(27))
        val post = HillCategory(
            _1891 = _1891,
            _1921 = _1921,
            _1933 = _1933,
            _1953 = _1953,
            _1969 = _1969,
            _1974 = _1974,
            _1981 = _1981,
            _1984 = _1984,
            _1990 = _1990,
            _1997 = _1997,
            post1997 = post1997,
        )

        val comments = line.get(28)

        return Mountain(
            number = nr,
            doBIHNumber = dobih,
            externalResources = externalResources,
            name = name,
            section = section,
            height = heights,
            mapScale = scale,
            gridRef = grid,
            coordinate = coord,
            hillCategory = post,
            comments = comments
        )
    }

    override fun parse(lines: List<List<String>>): List<Mountain> {
        //no data inside
        if (lines.isEmpty()) return emptyList()
        //only header or no header line
        if (lines.size == 1) return emptyList()

        val headers = lines.first()
        val dataRows = lines.drop(1)

        return parseRows(dataRows, headers)
    }

}
