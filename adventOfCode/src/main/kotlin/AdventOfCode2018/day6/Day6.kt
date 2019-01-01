package AdventOfCode2018.day6

import java.io.File
import kotlin.math.absoluteValue

data class Point(
        val x: Int,
        val y: Int,
        var destinationId: Int? = null,
        var closestId: Int? = null
) {
    infix fun distance(other: Point): Int {
        return (other.x - x).absoluteValue + (other.y - y).absoluteValue
    }
}

class Grid(val width: Int, val height: Int, val destinations: List<Point> = listOf()) {
    val points = Array(height) { y ->
        Array(width) { x -> Point(x, y) }
    }

    val excludeIds = mutableSetOf<Int>()

    init {
        destinations.forEach {
            points[it.y][it.x] = it
            it.closestId = it.destinationId
        }
    }

    override fun toString(): String {
        return points.map { rows ->
            rows.joinToString("") { it.destinationId?.toString() ?: "·" }
        }.joinToString("\n")
    }

    fun findClosest() {
        points.flatten().forEach { point ->
            val closest = destinations.minBy { destination -> point distance destination }
            point.closestId = closest?.destinationId
        }
    }

    fun get(x: Int, y: Int): Point = points[y][x]

    fun filterOpenArea() {
        listOf(
                (0 until width).map { x -> get(x, 0) }, // top line
                (0 until width).map { x -> get(x, height - 1) }, // bottom line
                (0 until height).map { y -> get(0, y) }, // left line
                (0 until height).map { y -> get(width - 1, y) } // right line
        )

        excludeIds.addAll(points[0].mapNotNull(Point::closestId).distinct())
        excludeIds.addAll(points[0].mapNotNull(Point::closestId).distinct())
    }
}

fun readPoints(lines: List<String>): List<Point> {
    return lines.mapIndexed { idx, line ->
        line.split(", ")
                .map(String::toInt)
                .let { (x, y) -> Point(x, y, idx) }
    }
}

fun readGrid(file: File = File("input", "day6.txt")): Grid {
    val destinations = readPoints(file.readLines())

    val width = (destinations.map(Point::x).max() ?: 0) + 1
    val height = (destinations.map(Point::y).max() ?: 0) + 1

    return Grid(width, height, destinations)
}