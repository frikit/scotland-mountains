package org.github.frikit.services

import org.github.frikit.models.database.Mountain
import org.github.frikit.models.database.MountainTopType
import org.springframework.stereotype.Service

class FilterService {

    companion object {
        fun List<Mountain>.filterDataByHillType(byMountainType: MountainTopType?): List<Mountain> {
            return if (byMountainType == null) {
                this.filter { it.hillCategory.post1997 != MountainTopType.EITHER }
            } else {
                this.filter { it.hillCategory.post1997 != MountainTopType.EITHER }
                    .filter { it.hillCategory.post1997 == byMountainType }
            }
        }

        fun List<Mountain>.filterDataByHillHeight(min: Double, max: Double): List<Mountain> {
            if (min >= max) throw RuntimeException("Min should be < Max! min=$min and max=$max")
            return this.filter { it.height.metres in min..max }
        }
    }

}
