package AdventOfCode2018.day6

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day6Test {

    @Test
    fun testDistance() {
        Point(1, 1) distance Point(3, 1) shouldBe 2
        Point(1, 1) distance Point(3, 2) shouldBe 3
        Point(-1, -1) distance Point(1, 1) shouldBe 4
    }

    @Test
    fun testReadPoints() {
        val points = readPoints("""1, 1
                    |1, 6
                    |8, 3""".trimMargin().split("\n"))
        points shouldEqual listOf(
                Point(1, 1, 0),
                Point(1, 6, 1),
                Point(8, 3, 2)
        )
    }

    @Test
    fun testGrid() {
        val destinations = readPoints("""1, 1
            |1, 6
            |8, 3
            |3, 4
            |5, 5
            |8, 9""".trimMargin().split("\n"))

        Grid(10, 10, destinations).toString() shouldEqual """
            |··········
            |·0········
            |··········
            |········2·
            |···3······
            |·····4····
            |·1········
            |··········
            |··········
            |········5·""".trimMargin()
    }

    @Test
    fun testResolvePart1() {
        val grid = readGrid(File("../input","day6.txt"))
        grid.largestAreaSize shouldEqual 3569
    }
}