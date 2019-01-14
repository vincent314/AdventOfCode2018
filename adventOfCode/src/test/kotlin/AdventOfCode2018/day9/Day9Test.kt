package AdventOfCode2018.day9

import AdventOfCode2018.Solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day9Test {

    val solution = Solutions.readFile().day9

    @Test
    fun testNewIndex() {
        Circle(mutableListOf(0, 1, 2, 3, 5))
                .apply {
                    newIndex shouldBe 2
                }
        Circle()
                .apply {
                    newIndex shouldBe 1
                }
        Circle(mutableListOf(0, 1), 1)
                .apply {
                    newIndex shouldBe 1
                }
        Circle(mutableListOf(0, 2, 1), 1)
                .apply {
                    newIndex shouldBe 3
                }
    }

    @Test
    fun testToString() {
        Circle(1, 0, 4, 2, 1, 3).toString() shouldEqual " 0 (4) 2  1  3 "
    }

    @Test
    fun testAddMarble() {
        val circle = Circle()
        (1..22).map { marble ->
            circle.add(marble)
            circle.toString()
        } shouldEqual listOf(
                " 0 (1)",
                " 0 (2) 1 ",
                " 0  2  1 (3)",
                " 0 (4) 2  1  3 ",
                " 0  4  2 (5) 1  3 ",
                " 0  4  2  5  1 (6) 3 ",
                " 0  4  2  5  1  6  3 (7)",
                " 0 (8) 4  2  5  1  6  3  7 ",
                " 0  8  4 (9) 2  5  1  6  3  7 ",
                " 0  8  4  9  2 (10) 5  1  6  3  7 ",
                " 0  8  4  9  2  10  5 (11) 1  6  3  7 ",
                " 0  8  4  9  2  10  5  11  1 (12) 6  3  7 ",
                " 0  8  4  9  2  10  5  11  1  12  6 (13) 3  7 ",
                " 0  8  4  9  2  10  5  11  1  12  6  13  3 (14) 7 ",
                " 0  8  4  9  2  10  5  11  1  12  6  13  3  14  7 (15)",
                " 0 (16) 8  4  9  2  10  5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8 (17) 4  9  2  10  5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8  17  4 (18) 9  2  10  5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8  17  4  18  9 (19) 2  10  5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8  17  4  18  9  19  2 (20) 10  5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8  17  4  18  9  19  2  20  10 (21) 5  11  1  12  6  13  3  14  7  15 ",
                " 0  16  8  17  4  18  9  19  2  20  10  21  5 (22) 11  1  12  6  13  3  14  7  15 "
        )
    }

    @Test
    fun testAdd23() {
        val circle = Circle(13, 0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15)
        circle.add(23)
        circle.toString() shouldEqual " 0  16  8  17  4  18 (19) 2  20  10  21  5  22  11  1  12  6  13  3  14  7  15 "
    }

    @Test
    fun testPlay() {
        play(7, 25).max() shouldEqual 32
        play(10,1618).max() shouldEqual 8317
        play(13,7999).max() shouldEqual 146373
        play(17,1104).max() shouldEqual 2764
        play(21,6111).max() shouldEqual 54718
        play(30,5807).max() shouldEqual 37305
    }

    @Test
    fun testResolvePart1() {
        play(File("../input","day9.txt").readText()).max() shouldEqual solution.part1
    }

    @Test
    fun testResolvePart2(){
        play(File("../input","day9.txt").readText(), 100).max() shouldEqual solution.part2
    }
}
