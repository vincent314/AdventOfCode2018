package adventofcode2018.day15

import adventofcode2018.day15.SquareType.*

typealias Matrix = List<List<SquareType>>

class Area(map: String) {
    var matrix: Matrix = listOf(listOf())

    init {
        matrix = map.split("\n")
                .map { row ->
                    row.map {
                        when (it) {
                            '.' -> EMPTY
                            '#' -> WALL
                            'E' -> ELF
                            'G' -> GOBELIN
                            else -> EMPTY
                        }
                    }
                }
    }

    override fun toString(): String {
        return matrix.joinToString("\n") { row ->
            row.map(SquareType::value).joinToString("")
        }
    }
}
