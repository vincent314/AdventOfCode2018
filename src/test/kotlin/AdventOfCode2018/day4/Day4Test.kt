package AdventOfCode2018.day4

import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun testParseDate() {
        val result = parseDate("[1518-08-25 00:52] wakes up")
        assertEquals("1518-08-25T00:52", result.toString())
    }

    @Test
    fun testParseLogEntry() {
        assertEquals(
                LogEntry(ActionEnum.WAKES_UP,
                        LocalDateTime.of(1518, 6, 10, 0, 53),
                        0
                ),
                parseLogEntry("[1518-06-10 00:53] wakes up")
        )
        assertEquals(
                LogEntry(ActionEnum.BEGINS_SHIFT,
                        LocalDateTime.of(1518, 5, 22, 23, 53),
                        2441),
                parseLogEntry("[1518-05-22 23:53] Guard #2441 begins shift")
        )

        assertEquals(
                LogEntry(ActionEnum.FALLS_ASLEEP,
                        LocalDateTime.of(1518, 9, 26, 0, 39),
                        0),
                parseLogEntry("[1518-09-26 00:39] falls asleep")
        )
    }

    @Test
    fun testGetGuardSleepRanges(){
        val logEntries = readEntries("""
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
        """.trimIndent().split("\n"))
        val result = logEntries.getGuardSleepRanges()
        assertEquals(listOf(5..25,30..55,24..29),result)
    }

    @Test
    fun testFindOptimised(){
        assertEquals(GuardSleepInfo(24,53), listOf(5..25,30..55,24..29).findMinute())
    }

    @Test
    fun testResolveExample(){
        val logEntries = readEntries("""
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
        """.trimIndent().split("\n"))

        assertEquals(240, resolvePuzzlePart1(logEntries))
    }

    @Test
    fun testResolvePart1(){
        val logEntries = readFile()
        assertEquals(140932, resolvePuzzlePart1(logEntries))
    }
}