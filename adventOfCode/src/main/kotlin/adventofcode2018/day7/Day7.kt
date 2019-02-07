package adventofcode2018.day7

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.File
import java.util.*

data class Vertex(
        val step: Char,
        val next: MutableSet<Vertex> = TreeSet(),
        @JsonIgnore
        val previous: MutableSet<Vertex> = TreeSet(),
        var duration: Int = 1
) : Comparable<Vertex> {
    override fun compareTo(other: Vertex): Int = this.step.compareTo(other.step)
    override fun toString(): String {
        return "${previous.joinToString { it.step.toString() }} <-- $step --> ${next.joinToString { it.step.toString() }}"
    }
}

class Worker(val vertex: Vertex) {
    fun process(): Vertex? = vertex.takeIf { --it.duration == 0 }
}

fun String.parseInstruction(): Pair<Char, Char> {
    val (_, from, to) = Regex("""Step (\w) must be finished before step (\w) can begin\.""")
            .matchEntire(this)
            ?.groupValues
            ?: throw Exception("Invalid instruction format : $this")
    return from.first() to to.first()
}

fun readFile(file: File = File("input", "day7.txt"), withDuration: Boolean = false, baseDuration: Int = 0): MutableMap<Char, Vertex> {
    val vertices: MutableMap<Char, Vertex> = mutableMapOf()
    file.readLines()
            .asSequence()
            .map(String::parseInstruction)
            .forEach { (from, to) ->
                val fromVertex: Vertex
                val toVertex: Vertex
                if (withDuration) {
                    fromVertex = vertices.getOrPut(from) { Vertex(from, duration = baseDuration + 1 + (from - 'A')) }
                    toVertex = vertices.getOrPut(to) { Vertex(to, duration = baseDuration + 1 + (to - 'A')) }
                } else {
                    fromVertex = vertices.getOrPut(from) { Vertex(from) }
                    toVertex = vertices.getOrPut(to) { Vertex(to) }
                }
                fromVertex.next += toVertex
                toVertex.previous += fromVertex
            }
    return vertices
}

fun Map<Char, Vertex>.available(workers: Array<Worker?>) = this.values
        .filter { vertex -> vertex.previous.isEmpty() && workers.find { it?.vertex == vertex } == null }
        .sorted()

fun MutableMap<Char, Vertex>.complete(vertex: Vertex) {
    values.forEach { it.previous.remove(vertex) }
    remove(vertex.step)
}

fun getSequence(vertices: MutableMap<Char, Vertex>, nbWorkers: Int = 1): Pair<String, Int> {
    val sequence: MutableList<Vertex> = mutableListOf()
    val workers = Array<Worker?>(nbWorkers) { null }
//    val availableList = LinkedList(vertices.available())
    var second = 0
    while (vertices.isNotEmpty()) {
        val availableList = vertices.available(workers).toMutableList()
        workers.forEachIndexed { index, worker ->
            if (availableList.isNotEmpty() && worker == null) {
                val vertex = availableList.removeAt(0)
                workers[index] = Worker(vertex)
            }
        }

        print(second, workers, sequence)

        workers.forEachIndexed { index, worker ->
            worker?.process()
                    ?.also { completed ->
                        sequence += completed
                        vertices.complete(completed)
                        workers[index] = null
                    }

        }

//
//        workers.forEachIndexed { index, worker ->
//            val availableList = vertices.available(workers)
//            val maybeVertexCompleted = if (worker != null) {
//                worker.process()
//            } else {
//                val vertex = availableList.firstOrNull()
//                vertex?.let {
//                    val newWorker = Worker(it)
//                    workers[index] = newWorker
//                    newWorker.process()
//                }
//            }
//            maybeVertexCompleted?.also {
//                sequence += it
//                vertices.complete(it)
//                workers[index] = null
//            }
//        }
        second++
    }
    return sequence.map { it.step }.joinToString("") to second
}

fun print(second: Int, workers: Array<Worker?>, sequence: List<Vertex>) {
    print(String.format("%1$4d", second))
    print(workers.joinToString("") {
        String.format("%1$5s(%2$2d)", it?.vertex?.step?.toString() ?: ".", it?.vertex?.duration ?: 0)
    })
    print("    ")
    println(sequence.joinToString("") { it.step.toString() })
}

