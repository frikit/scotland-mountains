package org.github.frikit.models

import org.github.frikit.models.database.Mountain
import org.github.frikit.models.response.MountainShortInfo

class ModelsMappers {

    companion object {
        fun List<Mountain>.mapToMinimalInfo(): List<MountainShortInfo> {
            return this.map { m ->
                MountainShortInfo(
                    name = m.name,
                    heightMeters = m.height.metres,
                    hillCategory = m.hillCategory.post1997,
                    gridRef = m.gridRef.ref
                )
            }
        }
    }

}
