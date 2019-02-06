package adventofcode2018.day7

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.File
import java.util.*

data class Vertex(
        val value: Char,
        val next: MutableSet<Vertex> = TreeSet(),
        @JsonIgnore
        val previous: MutableSet<Vertex> = TreeSet()
) : Comparable<Vertex> {
    override fun compareTo(other: Vertex): Int = this.value.compareTo(other.value)
    override fun toString(): String {
        return "${previous.joinToString { it.value.toString() }} <-- $value --> ${next.joinToString { it.value.toString() }}"
    }
}

fun String.parseInstruction(): Pair<Char, Char> {
    val (_, from, to) = Regex("""Step (\w) must be finished before step (\w) can begin\.""")
            .matchEntire(this)
            ?.groupValues
            ?: throw Exception("Invalid instruction format : $this")
    return from.first() to to.first()
}

fun readFile(file: File = File("input", "day7.txt")): MutableMap<Char, Vertex> {
    val vertices: MutableMap<Char, Vertex> = mutableMapOf()
    file.readLines()
            .asSequence()
            .map(String::parseInstruction)
            .forEach { (from, to) ->
                val fromVertex = vertices.getOrPut(from) { Vertex(from) }
                val toVertex = vertices.getOrPut(to) { Vertex(to) }
                fromVertex.next += toVertex
                toVertex.previous += fromVertex
            }
    return vertices
}

fun Map<Char, Vertex>.available() = this.values.filter { it.previous.isEmpty() }.sorted()
fun MutableMap<Char, Vertex>.complete(vertex: Vertex) {
    values.forEach { it.previous.remove(vertex) }
    remove(vertex.value)
}

fun getSequence(vertices: MutableMap<Char, Vertex>): String {
    var availableList = vertices.available()
    val sequence: MutableList<Vertex> = mutableListOf()

    while (availableList.isNotEmpty()) {
        val vertex = availableList.first()
        sequence += vertex
        vertices.complete(vertex)
        availableList = vertices.available()
    }
    return sequence.map { it.value }.joinToString("")
}
