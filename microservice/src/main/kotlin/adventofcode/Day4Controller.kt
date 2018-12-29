package adventofcode

import AdventOfCode2018.day4.LogEntry
import AdventOfCode2018.day4.readFile
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/day4")
class Day4Controller {

    @Get("/entries")
    fun index(): List<LogEntry> {
        return readFile()
    }
}