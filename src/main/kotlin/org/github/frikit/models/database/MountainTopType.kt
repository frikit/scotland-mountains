package org.github.frikit.models.database

enum class MountainTopType {
    MUN,
    TOP,
    EITHER;

    companion object {
        fun parse(input: String): MountainTopType {
            return when(input.trim().toUpperCase()) {
                "MUN" -> MUN
                "TOP" -> TOP
                else -> EITHER
            }
        }
    }
}
