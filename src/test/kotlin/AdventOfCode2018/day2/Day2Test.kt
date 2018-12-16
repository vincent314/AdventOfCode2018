package AdventOfCode2018.day2

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day2Test {

    @Test
    fun testCount() {
        assertEquals(
                mutableMapOf('a' to 2, 'b' to 1, 'c' to 1),
                "abca".countLetters().filterValues { it > 0 }
        )
    }

    @Test
    fun testWords() {
        assertFalse("abcdef" % 2)
        assertFalse("abcdef" % 3)

        assertTrue("bababc" % 2)
        assertTrue("bababc" % 3)

        assertTrue("abbcde" % 2)
        assertFalse("abbcde" % 3)

        assertFalse("abcccd" % 2)
        assertTrue("abcccd" % 3)

        assertTrue("aabcdd" % 2)
        assertFalse("aabcdd" % 3)

        assertTrue("abcdee" % 2)
        assertFalse("abcdee" % 3)

        assertFalse("ababab" % 2)
        assertTrue("ababab" % 3)
    }

    @Test
    fun testHashCode() {
        val result = hash(listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"))
        assertEquals(12, result)
    }

    @Test
    fun testPuzzlePart1() {
        val result = hash(readFile())
        assertEquals(5928, result)
    }

    @Test
    fun testDistance() {
        assertEquals(0, "abcdef" distance "abcdef")
        assertEquals(1, "abcdef" distance "abcyef")
        assertEquals(2, "abcdef" distance "abxyef")
    }

    @Test
    fun testFindCloseIds() {
        val result = findCloseIds(listOf(
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz"
        ))
        assertEquals(listOf("fghij" to "fguij"), result)
    }

    @Test
    fun testCommonChars(){
        assertEquals("fgij",commonChars("fghij" to "fguij"))
    }

    @Test
    fun testPuzzlePart2(){
        val result = commonChars(findCloseIds(readFile()).first())
        assertEquals("bqlporuexkwzyabnmgjqctvfs", result)
    }
}