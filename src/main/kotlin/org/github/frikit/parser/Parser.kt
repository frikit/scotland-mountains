package org.github.frikit.parser

import org.github.frikit.models.Mountain

interface Parser {

    fun parse(rows: List<List<String>>): List<Mountain>
}
