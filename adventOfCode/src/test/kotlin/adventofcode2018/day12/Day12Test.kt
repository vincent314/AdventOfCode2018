package adventofcode2018.day12

import adventofcode2018.solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.nield.kotlinstatistics.simpleRegression
import java.io.File

class Day12Test {

    private val solution = solutions().day12

    @Test
    fun `should get and set value with an offset`() {
        val potArray = PotArray("#..#.........")
        potArray.toString() shouldEqual "...#..#"
    }

    @Test
    fun `should read instructions file`() {
        val potArray = readFile(File("../input", "day12-example.txt"))

        potArray.toString() shouldEqual "...#..#.#..##......###...###"

        (-2..5).map { potArray[it] }.toList() shouldEqual "..#..#.#".parsePots()

        potArray.instructions shouldEqual mapOf(
                "...##" to true,
                "..#.." to true,
                ".#..." to true,
                ".#.#." to true,
                ".#.##" to true,
                ".##.." to true,
                ".####" to true,
                "#.#.#" to true,
                "#.###" to true,
                "##.#." to true,
                "##.##" to true,
                "###.." to true,
                "###.#" to true,
                "####." to true
        )
    }

    @Test
    fun `should get neighbourhoud`() {
        val potArray: PotArray = readFile(File("../input", "day12-example.txt"))
        potArray.getNeighborhood(0) shouldEqual "..#.."
        potArray.getNeighborhood(1) shouldEqual ".#..#"
        potArray.getNeighborhood(24) shouldEqual "###.."
    }

    @Test
    fun `should get next generation`() {
        val potArray: PotArray = readFile(File("../input", "day12-example.txt"))

        fun doNext(expected: String, nb: Int = 1) {
            potArray.next(nb)
            potArray.toString() shouldEqual expected
        }

        doNext("...#...#....#.....#..#..#..#")
        doNext("...##..##...##....#..#..#..##")
        doNext("..#.#...#..#.#....#..#..#...#")
        doNext("...#.#..#...#.#...#..#..##..##")
        doNext("....#...##...#.#..#..#...#...#")
        doNext("....##.#.#....#...#..##..##..##")
        doNext("...#..###.#...##..#...#...#...#")
        doNext(".#....##....#####...#######....#.#..##", 13)
    }

    @Test
    fun `should apply rules on left pots`() {
        val potArray = PotArray("##.....", mapOf("...##" to true))

        potArray.next()
        potArray.toString() shouldEqual "..#"
    }

//    @Test
//    fun `should get left pots signature`() {
//        var potArray = PotArray(5, mutableListOf(), leftPots = ".#".parsePots())
//        potArray.signature shouldEqual -1
//
//
//        potArray = PotArray(5, mutableListOf(), leftPots = ".##".parsePots())
//        potArray.signature shouldEqual -1 - 2
//
//        potArray = PotArray(5, "##".parsePots(), leftPots = "..#".parsePots())
//        potArray.signature shouldEqual -2 + 0 + 1
//    }

    @Test
    fun `should resolve puzzle example`() {
        val potArray: PotArray = readFile(File("../input", "day12-example.txt"))
        potArray.next(20)
        potArray.toString() shouldEqual ".#....##....#####...#######....#.#..##"
        potArray.signature shouldEqual 325
    }

    @Test
    fun `should resolve part1`() {
        val potArray: PotArray = readFile(File("../input", "day12.txt"))
        potArray.next(20)
        potArray.signature shouldEqual solution.part1
    }

    @Test
    fun `should resolve part2 - extrapolate`() {
        val potArray: PotArray = readFile(File("../input", "day12.txt"))
        val result = (1000..10_000 step 1_000).map {
            potArray.next(1_000)
            it to potArray.signature
        }
                .simpleRegression()
                .predict(50_000_000_000.0)
                .toLong()
        result shouldEqual solution.part2

    }
}
