package AdventOfCode2018.day8

import java.io.File

data class Node(val children: List<Node>, val metadata: List<Int>) {
    fun doSum(): Int {
        return this.metadata.sum() + this.children.sumBy(Node::doSum)
    }

    fun doSumPart2(): Int {
        return if (this.children.isEmpty()) {
            this.metadata.sum()
        } else {
            this.metadata
                    .mapNotNull { this.children.getOrNull(it - 1) }
                    .sumBy(Node::doSumPart2)
        }
    }
}

fun parseInput(file: File = File("../input", "day8.txt")): Node {
    return parseInput(file.readText())

}

fun parseInput(input: String): Node {
    val data = input.split(" ").map(String::toInt).toMutableList()
    return parseInput(data)
}

fun MutableList<Int>.dequeue(): Int = this.removeAt(0)

fun parseInput(data: MutableList<Int>): Node {
    val nbChildren = data.dequeue()
    val nbMetadata = data.dequeue()

    val children = (0 until nbChildren).map {
        parseInput(data)
    }
    val metadata = (0 until nbMetadata).map {
        data.dequeue()
    }
    return Node(children, metadata)
}

