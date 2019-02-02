package adventofcode2018.day9

import adventofcode2018.Solutions
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
        val score = circle.add(23)
        circle.toString() shouldEqual " 0  16  8  17  4  18 (19) 2  20  10  21  5  22  11  1  12  6  13  3  14  7  15 "
        score shouldEqual 32
    }

    @Test
    fun testPlay() {
        play(7, 25).max() shouldEqual 32
        play(10, 1618).max() shouldEqual 8317
        play(13, 7999).max() shouldEqual 146373
        play(17, 1104).max() shouldEqual 2764
        play(21, 6111).max() shouldEqual 54718
        play(30, 5807).max() shouldEqual 37305
    }

    @Test
    fun testResolvePart1() {
        play(File("../input", "day9.txt").readText()).max() shouldEqual solution.part1
    }

    @Test
    fun testResolvePart2() {
        play(File("../input", "day9.txt").readText(), 100).max() shouldEqual solution.part2
    }

    @Test
    fun testDeque() {
        Deque().apply { appendAfter(42) } shouldEqual Deque(42)
        Deque(0, 1, 2).apply { appendAfter(10, 11, 12) } shouldEqual Deque(0, 1, 2, 10, 11, 12)
        Deque().apply { appendBefore(4, 5, 6) } shouldEqual Deque(listOf(6, 5, 4))
        Deque(0, 1, 2).apply { appendBefore(10, 11, 12) } shouldEqual Deque(12, 11, 10, 0, 1, 2)

        var deque = Deque(10, 11, 12, 13)
        deque.removeBefore(2) shouldEqual listOf(10, 11)
        deque shouldEqual Deque(12, 13)

        deque = Deque(10, 11, 12, 13)
        deque.removeAfter(2) shouldEqual listOf(13, 12)
        deque shouldEqual Deque(10, 11)
    }

    @Test
    fun testAddSimpleMarble() {
        val circle = Circle()
        circle.add(1)
        circle.toString() shouldEqual " 0 (1)"
        circle.add(2)
        circle.toString() shouldEqual " 0 (2) 1 "

    }

    @Test
    fun `should add marble %23 in the clockwise list`() {
        val state = "0  90  39 91 16  40  8  41  42  4  47  43  48  18  49  44  50  19  51  45  52  2  53  24  54  20  55" +
                "  25  56  10  57  26  58  21  59  27  60  5  61  28  62  22  63  29  64  65  30  70  66  71  1  72  67  73" +
                "  31  74  68  75  12  76  32  77  6  78  33  79  13  80  34  81  3  82  35  83  14  84  36  85  7  86" +
                "  37  87  15  88  38  89"

        val input = state.split(Regex("\\s+")).map(String::toInt)
        val circle = Circle(input,3)
        val score = circle.add(92)
        score shouldEqual 92 + 15
        circle.toString() shouldEqual " 0  90  39  91  16  40  8  41  42  4  47  43  48  18  49  44  50  19  51  45  52  2  " +
                "53  24  54  20  55  25  56  10  57  26  58  21  59  27  60  5  61  28  62  22  63  29  64  65  30  70  " +
                "66  71  1  72  67  73  31  74  68  75  12  76  32  77  6  78  33  79  13  80  34  81  3  82  35  83  " +
                "14  84  36  85  7  86  37  87 (88) 38  89 "
    }

    @Test
    fun `should add marbles at begining or end`(){
        var circle = Circle(0,0,1,2,3)
        circle.add(4)
        circle.toString() shouldEqual " 0  1 (4) 2  3 "

        circle = Circle(5, 0,1,2,3,4,5)
        circle.add(6)
        circle.toString() shouldEqual " 0 (6) 1  2  3  4  5 "

        circle = Circle(0, 0,1,2,3,4,5,6,7,8,9,10)
        circle.add(23)
        circle.toString() shouldEqual " 0  1  2  3 (5) 6  7  8  9  10 "
    }
}
