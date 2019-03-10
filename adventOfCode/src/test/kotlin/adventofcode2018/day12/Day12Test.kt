package adventofcode2018.day12

import adventofcode2018.solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Ignore
import org.junit.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day12Test {

    private val solution = solutions().day12

    @Test
    fun `should get and set value with an offset`() {
        val potArray = PotArray("#..#.........")
        potArray.toString() shouldEqual "...#..#........."
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

        fun doNext(expected: String, nb: Long = 1L) {
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
    @Ignore
    fun `should resolve part2`() {
        val potArray: PotArray = readFile(File("../input", "day12.txt"))
        println("part2 execution time = " + measureTimeMillis {
            potArray.next(5_000_000)
        })
        potArray.signature shouldEqual solution.part2
    }

    @Test
    fun `should test offset list`() {
        var list = OffsetList(listOf(true, false, true, false), 2)

        list.toString() shouldEqual "#.#."

        list[-3] shouldBe false
        list[-2] shouldBe true
        list[-1] shouldBe false
        list[0] shouldBe true
        list[1] shouldBe false
        list[2] shouldBe false

        list = OffsetList(".#..#.", 2)
        list.toString() shouldEqual "...#..#."
    }
}
