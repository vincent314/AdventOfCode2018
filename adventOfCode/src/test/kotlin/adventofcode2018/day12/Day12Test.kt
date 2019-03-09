package adventofcode2018.day12

import adventofcode2018.solutions
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day12Test {

    private val solution = solutions().day12

    @Test
    fun `should get and set value with an offset`() {
        val potArray = PotArray(initialCapacity = 12)
        potArray[0] = true
        potArray[3] = true

        potArray.toString() shouldEqual "...#..#........."
    }

    @Test
    fun `should read instructions file`() {
        val potArray = readFile(File("../input", "day12-example.txt"))

        potArray.toString() shouldEqual "...#..#.#..##......###...###"

        (-2..5).map { potArray[it] }.toBooleanArray() shouldEqual booleanArrayOf(false, false, true, false, false, true, false, true)

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

    @Test
    fun `should get left pots signature`() {
        var potArray = PotArray(5, mutableListOf(), leftPots = mutableListOf(false, true))
        potArray.signature shouldEqual -1


        potArray = PotArray(5, mutableListOf(), leftPots = mutableListOf(false, true, true))
        potArray.signature shouldEqual -1 - 2

        potArray = PotArray(5, mutableListOf(true, true), leftPots = mutableListOf(false, false, true))
        potArray.signature shouldEqual -2 + 0 + 1
    }

    @Test
    fun `should resolve puzzle example`() {
        val potArray: PotArray = readFile(File("../input", "day12-example.txt"))
        potArray.next(20)
        potArray.toString() shouldEqual ".#....##....#####...#######....#.#..##"
        potArray.leftPots shouldEqual mutableListOf(false, false, true, false)
        potArray.signature shouldEqual 325
    }

    @Test
    fun `should resolve part1`(){
        val potArray: PotArray = readFile(File("../input", "day12.txt"))
        potArray.next(20)
        potArray.signature shouldEqual solution.part1
    }
}
