package AdventOfCode2018.day5

import java.io.File
import java.util.*

fun isCombine(first: Char?, second: Char): Boolean {
    if (first == null) {
        return false
    }
    val sign1 = first in 'a'..'z'
    val sign2 = second in 'a'..'z'

    return first.toLowerCase() == second.toLowerCase() && sign1 != sign2
}

fun <T> ArrayList<T>.pop(): T? =
        if (isEmpty()) null
        else removeAt(size - 1)

fun process(polymer: String): String {
    val stack: ArrayList<Char> = arrayListOf()
    polymer.forEach { char ->
        val previous = stack.lastOrNull()
        if (isCombine(previous, char)) stack.pop() else stack.add(char)
    }
    return stack.joinToString("")
}

fun resolvePart1(file: File = File("../input", "day5.txt")): String =
        process(file.readText())

fun processByChar(polymer: String, filterRange: CharRange = 'a'..'z'): Map<Char, String> =
        filterRange.map { char ->
            val regex = Regex("[$char${char.toUpperCase()}]")
            val newPolymer = polymer.replace(regex, "")
            char to process(newPolymer)
        }.toMap()

fun resolvePart2(file: File = File("../input", "day5.txt")): Int =
        processByChar(file.readText())
                .values
                .map { it.count() }
                .min()
                ?: 0