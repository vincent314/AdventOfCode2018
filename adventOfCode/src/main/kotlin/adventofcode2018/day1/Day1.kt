package adventofcode2018.day1

import java.io.File
import java.util.*

fun readFile(file: File = File("input", "day1.txt")): List<Long> = file
        .readLines()
        .map(String::toLong)

fun calibrate(frequencies: List<Long>) = frequencies
        .fold(0L) { agg, value ->
            agg + value
        }

fun findTwice(frequencies: List<Long>): Long {
    val register = TreeSet<Long>().apply { add(0) }
    var current = 0L

    while(true) {
        frequencies.forEach { frequency ->
            current += frequency
            if (current in register) println("Found $current !")
            if (current in register) return current
            register.add(current)
        }
    }
}