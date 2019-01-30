package AdventOfCode2018.day10

data class Point(var position: Coord, val velocity: Coord) {
  constructor(x: Int, y: Int, vx: Int, vy: Int) : this(x to y, vx to vy)
}
