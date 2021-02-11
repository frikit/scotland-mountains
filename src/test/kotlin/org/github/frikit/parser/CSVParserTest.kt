package org.github.frikit.parser

import org.github.frikit.BaseTestClass
import org.github.frikit.models.*
import org.github.frikit.models.MountainTopType.MUN
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CSVParserTest : BaseTestClass() {

    private val parser = CSVParser()

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testEmptyResultWhenParseEmptyString() {
        assertEquals(emptyList<Mountain>(), parser.parse(emptyList()))
    }

    @Test
    fun testEmptyResultWhenParseOneLineAndItIsHeader() {
        assertEquals(emptyList<Mountain>(), parser.parse(listOf(listOf("header1", "header2"))))
    }

    @Test
    fun testEmptyResultWhenParseOneLineAndItIsDataLine() {
        assertEquals(emptyList<Mountain>(), parser.parse(listOf(listOf("1", "tango"))))
    }

    @Test
    fun testParseValidCSV() {
        val input = listOf(
            //headers
            listOf(
                "Running No",
                "DoBIH Number",
                "Streetmap",
                "Geograph",
                "Hill-bagging",
                "Name",
                "SMC Section",
                "RHB Section",
                "_Section",
                "Height (m)",
                "Height (ft)",
                "Map 1:50",
                "Map 1:25",
                "Grid Ref",
                "GridRefXY",
                "xcoord",
                "ycoord",
                "1891",
                "1921",
                "1933",
                "1953",
                "1969",
                "1974",
                "1981",
                "1984",
                "1990",
                "1997",
                "Post 1997",
                "Comments"
            ),
            //row 1
            listOf(
                "1",
                "1",
                "http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y",
                "http://www.geograph.org.uk/gridref/NN7732430857",
                "http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1",
                "Ben Chonzie",
                "1",
                "01A",
                "1.1",
                "931",
                "3054",
                "51 52",
                "OL47W 368W 379W",
                "NN773308",
                "NN7732430857",
                "277324",
                "730857",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                "MUN",
                ""
            )
        )
        val expected = Mountain(
            number = 1,
            doBIHNumber = 1,
            externalResources = ExternalResources(
                streetMapURL = "http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y",
                geographURL = "http://www.geograph.org.uk/gridref/NN7732430857",
                hillBaggingURL = "http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1"
            ),
            name = "Ben Chonzie",
            section = Section(smcSection = 1, rbhSection = "01A", _section = 1.1),
            height = Height(metres = 931.0, feet = 3054.0),
            mapScale = MapScale(
                scaleOneToFifty = listOf("51", "52"),
                scaleOneToTwentyFive = listOf("OL47W", "368W", "379W")
            ),
            gridRef = GridRef(ref = "NN773308", refXY = "NN7732430857"),
            coordinate = Coordinate(x = 277324, y = 730857),
            post = Post(
                _1891 = MUN,
                _1921 = MUN,
                _1933 = MUN,
                _1953 = MUN,
                _1969 = MUN,
                _1974 = MUN,
                _1981 = MUN,
                _1984 = MUN,
                _1990 = MUN,
                _1997 = MUN,
                post1997 = MUN
            ),
            comments = ""
        )
        assertEquals(expected, parser.parse(input).first())
    }
}
