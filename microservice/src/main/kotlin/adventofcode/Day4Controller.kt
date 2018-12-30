package adventofcode

import AdventOfCode2018.day4.LogEntry
import AdventOfCode2018.day4.countSleepCountByGuard
import AdventOfCode2018.day4.readFile
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/day4")
class Day4Controller {

    @Get("/entries")
    fun entries(): List<LogEntry> {
        return readFile()
    }

    @Get("/sleeps")
    fun guardSleeps(): Map<Int, Array<Int>> {
        return countSleepCountByGuard(readFile())
    }
}