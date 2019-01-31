package AdventOfCode2018.day10

import AdventOfCode2018.day10.common.Point
import AdventOfCode2018.day10.common.Sky
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day10Test {
  @Test
  fun `should parse input line`() {
    parseInput("position=< 9,  1> velocity=< 0,  2>") shouldEqual Point(9, 1, 0, 2)
  }

  @Test
  fun `should parse example file`() {
    readFile(File("../input", "day10-example.txt")) shouldEqual listOf(
            Point(9, 1, 0, 2),
            Point(7, 0, -1, 0),
            Point(3, -2, -1, 1),
            Point(6, 10, -2, -1),
            Point(2, -4, 2, 2),
            Point(-6, 10, 2, -2),
            Point(1, 8, 1, -1),
            Point(1, 7, 1, 0),
            Point(-3, 11, 1, -2),
            Point(7, 6, -1, -1),
            Point(-2, 3, 1, 0),
            Point(-4, 3, 2, 0),
            Point(10, -3, -1, 1),
            Point(5, 11, 1, -2),
            Point(4, 7, 0, -1),
            Point(8, -2, 0, 1),
            Point(15, 0, -2, 0),
            Point(1, 6, 1, 0),
            Point(8, 9, 0, -1),
            Point(3, 3, -1, 1),
            Point(0, 5, 0, -1),
            Point(-2, 2, 2, 0),
            Point(5, -2, 1, 2),
            Point(1, 4, 2, 1),
            Point(-2, 7, 2, -2),
            Point(3, 6, -1, -1),
            Point(5, 0, 1, 0),
            Point(-6, 0, 2, 0),
            Point(5, 9, 1, -2),
            Point(14, 7, -2, 0),
            Point(-3, 6, 2, -1)
    )
  }

  @Test
  fun `should display initial state`() {
    val points = readFile(File("../input", "day10-example.txt"))
    val sky = Sky(points)
    sky.toString() shouldEqual """
        |........#.............
        |................#.....
        |.........#.#..#.......
        |......................
        |#..........#.#.......#
        |...............#......
        |....#.................
        |..#.#....#............
        |.......#..............
        |......#...............
        |...#...#.#...#........
        |....#..#..#.........#.
        |.......#..............
        |...........#..#.......
        |#...........#.........
        |...#.......#..........""".trimMargin()
  }

  @Test
  fun `should move light by 1 second`() {
    val points = readFile(File("../input", "day10-example.txt"))
    val sky = Sky(points)
    sky.next()
    sky.toString() shouldEqual """
        |......................
        |......................
        |..........#....#......
        |........#.....#.......
        |..#.........#......#..
        |......................
        |......#...............
        |....##.........#......
        |......#.#.............
        |.....##.##..#.........
        |........#.#...........
        |........#...#.....#...
        |..#...........#.......
        |....#.....#.#.........
        |......................
        |......................""".trimMargin()
  }
}
