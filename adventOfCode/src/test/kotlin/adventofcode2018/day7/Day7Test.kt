package adventofcode2018.day7

import adventofcode2018.Solutions
import adventofcode2018.solutions
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day7Test {

    val solution = solutions().day7

    @Test
    fun testReadFileExample() {
        val vertices = readFile(File("../input", "day7-example.txt"))

        vertices.values.joinToString("\n") shouldEqual """ <-- C --> A, F
                                                            |C <-- A --> B, D
                                                            |C <-- F --> E
                                                            |A <-- B --> E
                                                            |A <-- D --> E
                                                            |B, D, F <-- E --> """.trimMargin()
    }

    @Test
    fun testBuildSequenceExample() {
        val vertices = readFile(File("../input", "day7-example.txt"))

        getSequence(vertices) shouldEqual "CABDFE"
    }

    @Test
    fun testResolvePart1() {
        val vertices = readFile(File("../input", "day7.txt"))

        val sequence = getSequence(vertices)
        sequence shouldEqual solution.part1
    }
}