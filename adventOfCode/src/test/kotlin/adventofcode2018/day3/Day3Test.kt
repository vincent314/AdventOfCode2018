package adventofcode2018.day3

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun testCreation() {
        val fabric = Fabric(3, 4)
        assertEquals("""
            ...
            ...
            ...
            ...
        """.trimIndent(),
                fabric.toString())
    }

    @Test
    fun testSet() {
        val fabric = Fabric(11, 9)
        fabric.set(Claim(123, 3, 2, 5, 4))
        assertEquals("""
            ...........
            ...........
            ...#####...
            ...#####...
            ...#####...
            ...#####...
            ...........
            ...........
            ...........
        """.trimIndent(), fabric.toString())
    }

    @Test
    fun testReadString() {
        assertEquals(Claim(1, 1, 3, 4, 4), Claim.from("#1 @ 1,3: 4x4"))
        assertEquals(Claim(2, 3, 1, 4, 4), Claim.from("#2 @ 3,1: 4x4"))
        assertEquals(Claim(3, 5, 5, 2, 2), Claim.from("#3 @ 5,5: 2x2"))
    }

    @Test
    fun testOverlap() {
        val fabric = Fabric(11,9)
        fabric.set(Claim.from("#123 @ 3,2: 5x4"))
        fabric.set(Claim.from("#124 @ 6,4: 3x3"))
        assertEquals("""
            ...........
            ...........
            ...#####...
            ...#####...
            ...###XX#..
            ...###XX#..
            ......###..
            ...........
            ...........
        """.trimIndent(), fabric.toString())
        assertEquals(4,fabric.countOverlaps())
    }

    @Test
    fun testPuzzlePart1And2(){
        val fabric = Fabric()
        fabric.readFile(File("../input", "day3.txt"))
        assertEquals(110383,fabric.countOverlaps())
        assertEquals(1,fabric.nonOverlapIds.size)
        assertEquals("129",fabric.nonOverlapIds.first())
    }
}