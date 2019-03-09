package adventofcode2018.day12

import java.io.File

fun readFile(file: File): PotArray {
    val lines = file.readLines().toMutableList()
    val initialState = lines.removeAt(0).removePrefix("initial state: ")

    // skip 2nd line
    lines.removeAt(0)
    val instructions = lines
            .asSequence()
            .onEach(::println)
            .map {
                val (before, after) = it.split(Regex(""" => """), 2)
                before to (after.first() == '#')
            }
            .toMap()

    return PotArray(initialState, instructions = instructions)
}


fun Boolean.toChar(): Char = if (this) '#' else '.'
fun List<Boolean>.toPotString(): String = joinToString("") { it.toChar().toString() }



