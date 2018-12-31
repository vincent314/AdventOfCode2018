package adventofcode

import AdventOfCode2018.day4.*
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/day4")
class Day4Controller {

    @Get("/entries")
    fun entries(): List<LogEntry> {
        return readFile()
    }

    @Get("/sleeps")
    fun guardSleeps() = getSleepsByGuard(readFile())

//    @Get("/mostAsleepByMinute")
//    fun findGuardByMinute() = findMostAsleepByMinute(getSleepsByGuard(readFile()))
}