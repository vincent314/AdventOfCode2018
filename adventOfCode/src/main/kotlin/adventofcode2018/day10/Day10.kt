package adventofcode2018.day10

import adventofcode2018.day10.common.Point
import java.io.File

typealias Coord = Pair<Int, Int>

fun parseInput(input: String): Point {
  println(input)
  val (x: String, y: String, vx: String, vy: String) = Regex("""position=<\s*([-\d]+),\s*([-\d]+)> velocity=<\s*([-\d]+),\s*([-\d]+)>""")
    .matchEntire(input)!!
    .destructured
  return Point(x.toInt(), y.toInt(), vx.toInt(), vy.toInt()
  )
}

fun readFile(file: File): List<Point> = file.readLines().map(::parseInput)
