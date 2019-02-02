package adventofcode2018.day10.common

import adventofcode2018.day10.Coord

data class Sky(val points: List<Point>) {

    val minX: Int = min { point -> point.position.first }
    val maxX: Int = max { point -> point.position.first }
    val minY: Int = min { point -> point.position.second }
    val maxY: Int = max { point -> point.position.second }

    var current = 0

    fun min(getter: (Point) -> Int): Int {
        return points.map { point -> getter(point) }.min()!!
    }

    fun max(getter: (Point) -> Int): Int {
        return points.map { point -> getter(point) }.max()!!
    }

    fun next(steps: Int = 1) {
        points.forEach {
            val (x, y) = it.position
            val (vx, vy) = it.velocity
            it.position = Coord(x + vx * steps, y + vy * steps)
        }
        current += steps
    }

    fun previous(steps: Int = 1) {
        next(steps * -1)
    }

    fun setAt(step:Int){
        next(step - current)
        current = step
    }


    override fun toString(): String {
        val buffer = StringBuilder()
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                if (points.find { it.position.first == x && it.position.second == y } == null) {
                    buffer.append(".")
                } else {
                    buffer.append("#")
                }
            }
            if (y != maxY) buffer.append("\n")
        }
        return buffer.toString()
    }
}
