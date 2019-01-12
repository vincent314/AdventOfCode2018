package AdventOfCode2018.day8

import AdventOfCode2018.Solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test

class Day8Test {

    val example = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
    val solutions = Solutions.readFile().day8

    @Test
    fun testExample() {
        val A = parseInput(example)
        A.children.size shouldBe 2
        A.metadata shouldEqual listOf(1, 1, 2)

        val (B, C) = A.children
        B.children.isEmpty() shouldBe true
        B.metadata shouldEqual listOf(10, 11, 12)
        C.children.size shouldBe 1
        C.metadata shouldEqual listOf(2)

        val (D) = C.children
        D.children.isEmpty() shouldBe true
        D.metadata shouldEqual listOf(99)
    }

    @Test
    fun testSum() {
        val root = parseInput(example)
        root.doSum() shouldEqual 138
    }

    @Test
    fun testResolvePart1() {
        val root = parseInput()
        root.doSum() shouldEqual solutions.part1
    }

    @Test
    fun testResolveExamplePart2() {
        val root = parseInput(example)
        root.doSumPart2() shouldEqual 66
    }

    @Test
    fun testResolvePart2(){
        val root = parseInput()
        root.doSumPart2() shouldEqual solutions.part2
    }
}