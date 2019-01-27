package AdventOfCode2018.day9

import java.util.*

class Deque(collection: Collection<Int>) : LinkedList<Int>(collection) {

    constructor() : this(listOf())

    constructor(vararg values: Int) : this(values.toList())

    fun appendAfter(vararg values: Int) {
        values.forEach { this.addLast(it) }
    }

    fun removeAfter(cutIndex: Int): List<Int> {
        return (cutIndex until this.size).map { this.removeLast() }
    }

    fun appendBefore(vararg values: Int) {
        values.forEach { this.push(it) }
    }

    fun removeBefore(cutIndex: Int): List<Int> {
        return (0 until cutIndex).map { this.pollFirst() }
    }
}

class Circle(
        marbles: List<Int> = listOf(0),
        var current: Int = 0
) {
    constructor(current: Int, vararg marbles: Int) : this(marbles.toMutableList(), current)

    var counterClockWise: Deque = Deque(marbles.slice(0 until current))
    var clockWise: Deque = Deque(marbles.slice(current until marbles.size))

    val newIndex: Int
        get() = (current + 1) % size + 1
    val newIndex23: Int
        get() {
            val i = current - 7
            return if (i < 0) i + size else i
        }
    val size: Int
        get() = counterClockWise.size + clockWise.size

    infix fun add(marble: Int): Int {
        if (marble % 23 == 0) {
            val index = newIndex23
            val bonusMarble = if (index > counterClockWise.size) {
                val cutIdx = index - counterClockWise.size + 1
                val transfert = clockWise.removeBefore(cutIdx)
                counterClockWise.appendAfter(*transfert.subList(0, transfert.size - 1).toIntArray())
                transfert.last()
            } else {
                val transfert = counterClockWise.removeAfter(index)
                clockWise.appendBefore(*transfert.subList(0, transfert.size - 1).toIntArray())
                transfert.last()
            }
            current = index
            return marble + bonusMarble
        } else {
            val index = newIndex
            if (index > counterClockWise.size) {
                val cutIdx = index - counterClockWise.size
                val transfert = clockWise.removeBefore(cutIdx)
                counterClockWise.appendAfter(*transfert.toIntArray())
                clockWise.appendBefore(marble)
            } else {
                val transfert = counterClockWise.removeAfter(index)
                clockWise.appendBefore(*transfert.toIntArray(), marble)
            }
            current = index
            return 0
        }

    }

    override fun toString(): String {
        fun listToString(list: List<Int>): String {
            return list.map { " $it " }.joinToString("")
        }

        return listToString(counterClockWise) + "(${clockWise.first()})" + listToString(clockWise.subList(1, clockWise.size))

    }

}


fun play(input: String, factor: Int = 1): Array<Long> {
    val (nbPlayers, nbMarbles) = Regex("(\\d+) players; last marble is worth (\\d+) points").matchEntire(input)
            ?.destructured!!
    println("Starting game : $nbPlayers players and $nbMarbles marbles")
    return play(nbPlayers.toInt(), nbMarbles.toInt() * factor)
}

fun play(nbPlayers: Int, nbMarbles: Int): Array<Long> {
    val start = Date().time
    val circle = Circle(marbles = listOf(0))
    val scores = Array(nbPlayers) { 0L }
    for (marble in 1..nbMarbles) {
        val player = (marble - 1) % nbPlayers
        scores[player] += circle.add(marble).toLong()
//        print("[${player}] ")
//        println(circle)
        if (marble % 100_000 == 0) {
            println("Marble : $marble")
        }
    }
    val duration = Date().time - start
    println("Total duration : $duration")
    return scores
}