package AdventOfCode2018.day4

import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class Day4Test {

    val example = """
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
        """.trimIndent().split("\n")

    val example2 = """
        [1518-11-01 00:00] Guard #10 begins shift
        [1518-11-01 00:05] falls asleep
        [1518-11-01 00:25] wakes up
        [1518-11-01 00:30] falls asleep
        [1518-11-01 00:55] wakes up
        [1518-11-01 23:58] Guard #99 begins shift
        [1518-11-02 00:40] falls asleep
        [1518-11-02 00:50] wakes up
        [1518-11-03 00:05] Guard #10 begins shift
        [1518-11-03 00:24] falls asleep
        [1518-11-03 00:29] wakes up
        [1518-11-04 00:02] Guard #99 begins shift
        [1518-11-04 00:36] falls asleep
        [1518-11-04 00:46] wakes up
        [1518-11-05 00:03] Guard #99 begins shift
        [1518-11-05 00:45] falls asleep
        [1518-11-05 00:55] wakes up
    """.trimIndent().split("\n")

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
    fun testGetGuardSleepRanges() {
        val logEntries = readEntries(example)
        val result = logEntries.getGuardSleepRanges()
        assertEquals(listOf(5..25, 30..55, 24..29), result)
    }

    @Test
    fun testFindOptimised() {
        assertEquals(GuardSleepInfo(24, 53), listOf(5..25, 30..55, 24..29).findMinute())
    }

    @Test
    fun testResolveExample() {
        val logEntries = readEntries(example)

        assertEquals(240, resolvePuzzlePart1(logEntries))
    }

    @Test
    fun testResolvePart1() {
        val logEntries = readFile()
        assertEquals(140932, resolvePuzzlePart1(logEntries))
    }

//    @Test
//    fun testGetMostAsleepGuard() {
//        val logEntries = readEntries(example2)
//        assertEquals(99 to 45, getMostAsleepGuard(logEntries))
//    }

    @Test
    fun testCountSleepCountByGuard() {
        val logEntries = readEntries(example2)
        val countByGuard = getSleepsByGuard(logEntries)
        assertEquals(
                "000001111111111111111111221111111111111111111111111111110000",
                countByGuard[10]?.sleepInfos?.map(GuardSleepInfo::count)?.joinToString(""))

        assertEquals(
                "000000000000000000000000000000000000111122222332222111110000",
                countByGuard[99]?.sleepInfos?.map(GuardSleepInfo::count)?.joinToString(""))

        assertEquals(
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........XXXXXXXXX",
                countByGuard[10]?.sleepInfos?.joinToString("") { if (it.isMax) "X" else "." })
        assertEquals(
                "........................................XXXXXXXXXXX.........",
                countByGuard[99]?.sleepInfos?.joinToString("") { if (it.isMax) "X" else "." })
    }

//    @Test
//    fun testResolveExamplePart2() {
//        val logEntries = readEntries(example2)
//        val (guardId, minute) = getMostAsleepGuard(logEntries)
//        assertEquals(99 * 45, guardId * minute)
//    }
//
//    @Test
//    fun testResolvePart2() {
//        val logEntries = readFile()
//        val (guardId, minute) = getMostAsleepGuard(logEntries)
//        println("Most probable guard asleep #$guardId, at minute $minute")
//        assertEquals(52833, guardId * minute)
//    }
}