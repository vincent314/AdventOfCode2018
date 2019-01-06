package AdventOfCode2018.day7

import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day7Test {

    @Test
    fun testReadFileExample() {
        val vertices = readFile(File("../input", "day7.txt"))

        vertices.values.forEach { println(it.toString()) }
    }

    @Test
    fun testBuildSequenceExample(){
        val vertices = readFile(File("../input", "day7-example.txt"))

        getSequence(vertices) shouldEqual "CABDFE"
    }

    @Test
    fun testResolvePart1(){
        val vertices = readFile(File("../input", "day7.txt"))

        getSequence(vertices) shouldEqual "BMOHTUFQZLCKPVRXWINAJDYESG"
    }
}