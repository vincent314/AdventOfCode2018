package AdventOfCode2018.day2

import java.io.File

fun readFile(file: File = File("input", "day2.txt")) = file.readLines()

fun createRegister() = ('a'..'z').map { it to 0 }.toMap().toMutableMap()

infix fun MutableMap<Char, Int>.inc(letter: Char) {
    val count = get(letter)!! + 1
    set(letter, count)
}

infix operator fun String.rem(count: Int): Boolean = countLetters().containsValue(count)

fun String.countLetters(): Map<Char, Int> {
    val register = createRegister()
    forEach(register::inc)
    return register.toMap()
}

fun hash(ids: List<String>): Int = ids.count { it % 2 } * ids.count { it % 3 }

infix fun String.distance(other: String): Int = zip(other).count { it.first != it.second }

fun findCloseIds(ids: List<String>): List<Pair<String, String>> {
    return ids.mapIndexedNotNull { idx, id ->
        val subList = ids.slice(idx + 1 until ids.size)
        val closeId = subList.find { it distance id == 1 }
        closeId?.let { id to closeId }
    }
}

fun commonChars(pair:Pair<String,String>) =
        (pair.first zip pair.second)
                .filter { it.first == it.second }
                .map { it.first }
                .joinToString("")

