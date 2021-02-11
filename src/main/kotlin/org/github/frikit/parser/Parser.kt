package org.github.frikit.parser

import org.github.frikit.models.database.Mountain

interface Parser {

    fun parse(rows: List<List<String>>): List<Mountain>
}
