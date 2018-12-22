package AdventOfCode2018.day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

enum class ActionEnum {
    BEGINS_SHIFT, FALLS_ASLEEP, WAKES_UP
}

data class LogEntry(var action: ActionEnum, var date: LocalDateTime, var guardId: Int = 0)

fun parseDate(entry: String): LocalDateTime =
        Regex("""\[(.+)] .+""")
                .find(entry)
                ?.groupValues
                ?.get(1)
                ?.let {
                    LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                }
                ?: throw Exception("Invalid date entry format $entry")


fun parseLogEntry(entry: String): LogEntry {
    val date = parseDate(entry)

    return when {
        Regex("""Guard #\d+ begins shift""") in entry -> {
            val id = Regex("""#(\d+)""").find(entry)?.groupValues?.get(1)?.toInt() ?: 0
            LogEntry(ActionEnum.BEGINS_SHIFT, date, id)
        }
        "falls asleep" in entry -> LogEntry(ActionEnum.FALLS_ASLEEP, date)
        "wakes up" in entry -> LogEntry(ActionEnum.WAKES_UP, date)
        else -> throw Exception("Unknown log entry")
    }
}

fun readFile(file: File = File("input", "day4.txt")): List<LogEntry> {
    return readEntries(file.readLines())

}

fun readEntries(entries: List<String>): List<LogEntry> {
    return entries
            .map { parseLogEntry(it) }
            .sortedBy(LogEntry::date)
            .also {
                var guardId = 0
                it.onEach { entry ->
                    if (entry.action == ActionEnum.BEGINS_SHIFT) {
                        guardId = entry.guardId
                    } else {
                        entry.guardId = guardId
                    }
                }
            }
}

fun List<LogEntry>.findHoursByGuard(): Map<Int, GuardSleepInfo?> =
        groupBy(LogEntry::guardId)
                .mapValues { (_, entries) -> entries.getGuardSleepRanges() }
                .mapValues { (_, ranges) -> ranges.findMinute() }
                .filterValues(Objects::nonNull)

fun List<LogEntry>.getGuardSleepRanges(): List<IntRange> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    println("#${get(0).guardId}")

    return asSequence()
            .filter { it.action != ActionEnum.BEGINS_SHIFT }
            .partition { it.action == ActionEnum.FALLS_ASLEEP }
            .let { (fallsAsleepList, wakesUpList) -> fallsAsleepList zip wakesUpList }
            .map { (from, to) ->
                val fromMinute = from.date.minute
                val toMinute = to.date.minute
                fromMinute..toMinute
            }
            .toList()
}

data class GuardSleepInfo(val minute: Int, val sleepTotal: Int)

fun List<IntRange>.findMinute(): GuardSleepInfo? {
    val sleepTotal = flatten().count()

    return flatten()
            .groupBy { it }
            .mapValues { (_, values) -> values.count() }
            .maxBy { (_, count) -> count }
            ?.let { (minute) -> GuardSleepInfo(minute, sleepTotal) }
}

fun resolvePuzzlePart1(entries: List<LogEntry>): Int =
        entries.findHoursByGuard()
                .toList()
                .maxBy { (_, info) -> info?.sleepTotal ?: 0 }
                ?.also(::println)
                ?.let { (guardId, info) -> guardId * (info?.minute?: 0) } ?: throw Exception("Solution not found")