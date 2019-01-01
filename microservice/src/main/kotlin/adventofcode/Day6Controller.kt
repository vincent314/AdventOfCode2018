package adventofcode

import AdventOfCode2018.day6.Grid
import AdventOfCode2018.day6.readGrid
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.io.File

@Controller("/day6")
class Day6Controller {

    @Get("/grid")
    fun getGrid(): Grid = readGrid()
            .also {
                it.findClosest()
            }

    @Get("/example")
    fun getGridExample(): Grid = readGrid(File("input", "day6-example.txt"))
            .also {
                it.findClosest()
            }
}