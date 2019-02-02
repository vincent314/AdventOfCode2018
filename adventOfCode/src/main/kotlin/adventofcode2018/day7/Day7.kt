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

fun readFile(file: File = File("input", "day7.txt")): Map<Char, Vertex> {
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
    return vertices.toMap()
}


fun getSequence(vertices: Map<Char, Vertex>): String {
    val values = vertices.values.filter { it.previous.isEmpty() }
    val set = TreeSet<Vertex>().also { it += values }
    val root = Vertex(' ', set)

    return getSequence(root).joinToString("").trim()
}

fun getSequence(vertex: Vertex, sequence: MutableList<Char> = mutableListOf()): MutableList<Char> {
    if(vertex.value in sequence){
        println("—— vertex ${vertex.value} already exists")
        return sequence
    }
    sequence += vertex.value
    vertex.next.forEach { next ->
        val allConditionsOK = next.previous.fold(true) { agg, previous ->
            agg && previous.value in sequence
        }
        if (allConditionsOK) {
            getSequence(next, sequence)
        }
    }
    return sequence
}