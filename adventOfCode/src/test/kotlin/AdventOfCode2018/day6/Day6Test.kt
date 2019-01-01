package AdventOfCode2018.day6

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test

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
            |·0····1···
            |··········
            |····3·····
            |··········
            |·····4····
            |··········
            |··········
            |···2·····5
            |··········""".trimMargin()
    }
}