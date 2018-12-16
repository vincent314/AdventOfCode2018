package AdventOfCode2018.day1

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun testReadFile() {
        val frequencies = readFile()
        assertEquals(977, frequencies.size)
        assertEquals(listOf(-12L, -6L, -12L, 1L, 3L, 3L, -1L, 10L, -8L, -9L), frequencies.slice(0..9))
    }

    @Test
    fun testCalibrate() {
        val frequencies = listOf(1L, -2L, +3L, +1L)
        val result = calibrate(frequencies)
        assertEquals(3, result)
    }

    @Test
    fun testPuzzlePart1() {
        val frequencies = readFile()
        val result = calibrate(frequencies)
        assertEquals(502, result)
    }

    @Test
    fun testFindTwice() {
        val frequencies = listOf(1L, -2L, 3L, 1L, 1L, -2L)
        val result = findTwice(frequencies)
        assertEquals(2L, result)
    }

    @Test
    fun testPuzzlePart2(){
        val frequencies = readFile()
        val result = findTwice(frequencies)
        assertEquals(71961L,result)
    }
}