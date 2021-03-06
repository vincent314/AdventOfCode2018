package adventofcode2018.day13

import adventofcode2018.day13.DirectionEnum.*

data class Position(val x: Int, val y: Int) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        val (x2, y2) = other
        return 1_000_000 * (y - y2) + x - x2
    }

    fun next(direction: DirectionEnum): Position {
        return when (direction) {
            RIGHT -> Position(x + 1, y)
            LEFT -> Position(x - 1, y)
            UP -> Position(x, y - 1)
            DOWN -> Position(x, y + 1)
        }
    }

    override fun toString(): String = "$x,$y"

}
