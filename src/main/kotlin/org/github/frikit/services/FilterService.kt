package org.github.frikit.services

import org.github.frikit.models.database.Mountain
import org.github.frikit.models.database.MountainTopType
import org.springframework.stereotype.Service

@Service
class FilterService {

    fun filterDataByHillType(
        input: List<Mountain>,
        byMountainType: MountainTopType?
    ): List<Mountain> {
        return if (byMountainType == null) {
            input.filter { it.hillCategory.post1997 != MountainTopType.EITHER }
        } else {
            input.filter { it.hillCategory.post1997 != MountainTopType.EITHER }
                .filter { it.hillCategory.post1997 == byMountainType }
        }
    }
}
