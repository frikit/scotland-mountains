package org.github.frikit.models

import org.github.frikit.BaseTestClass
import org.github.frikit.models.ModelsMappers.Companion.mapToMinimalInfo
import org.github.frikit.models.database.*
import org.github.frikit.models.response.MountainShortInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ModelsMappersTest : BaseTestClass() {

    @Test
    fun testMapFullMountainToShortVersion() {
        val input = Mountain(
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
            hillCategory = HillCategory(
                _1891 = MountainTopType.MUN,
                _1921 = MountainTopType.MUN,
                _1933 = MountainTopType.MUN,
                _1953 = MountainTopType.MUN,
                _1969 = MountainTopType.MUN,
                _1974 = MountainTopType.MUN,
                _1981 = MountainTopType.MUN,
                _1984 = MountainTopType.MUN,
                _1990 = MountainTopType.MUN,
                _1997 = MountainTopType.MUN,
                post1997 = MountainTopType.MUN
            ),
            comments = ""
        )
        val expected = MountainShortInfo(
            name = input.name,
            heightMeters = input.height.metres,
            hillCategory = input.hillCategory.post1997,
            gridRef = input.gridRef.ref
        )

        assertEquals(listOf(expected), listOf(input).mapToMinimalInfo())
    }
}
