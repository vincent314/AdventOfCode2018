package adventofcode2018.day12

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day12Test {

    @Test
    fun `should get and set value with an offset`() {
        val potArray = PotArray()
        potArray[0] = true
        potArray[3] = true

        potArray.toString() shouldEqual "...#..#........"
        potArray.size shouldBe 10
        potArray.pots.size shouldBe 15
    }

    @Test
    fun `should read instructions file`() {
        val potArray = readFile(File("../input", "day12-example.txt"))

        potArray.toString() shouldEqual "...#..#.#..##......###...###.."

        (-2..5).map { potArray[it] }.toBooleanArray() shouldEqual booleanArrayOf(false, false, true, false, false, true, false, true)

        potArray.instructions shouldEqual listOf(
                "...##" to '#',
                "..#.." to '#',
                ".#..." to '#',
                ".#.#." to '#',
                ".#.##" to '#',
                ".##.." to '#',
                ".####" to '#',
                "#.#.#" to '#',
                "#.###" to '#',
                "##.#." to '#',
                "##.##" to '#',
                "###.." to '#',
                "###.#" to '#',
                "####." to '#'
        )
    }

    @Test
    fun `should get neighbourhoud`() {
        val potArray = readFile(File("../input", "day12-example.txt"))
        potArray.getNeighbourhood(0).toPotString() shouldEqual "..#.."
        potArray.getNeighbourhood(1).toPotString() shouldEqual ".#..#"
        potArray.getNeighbourhood(24).toPotString() shouldEqual "###.."
    }
}
