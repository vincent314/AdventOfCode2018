package AdventOfCode2018.day9

import java.util.*
import kotlin.collections.ArrayList

class Circle(
        val marbles: MutableList<Int> = mutableListOf(0),
        var current: Int = 0
) {

    constructor(current: Int, vararg marbles: Int) : this(marbles.toMutableList(), current)

    val newIndex: Int
        get() = (current + 1) % marbles.size + 1

    infix fun add(marble: Int): Int {
        return if (marble % 23 == 0) {
            current -= 7
            if (current < 0) current += marbles.size
            val removeMarble = marbles[current]
            marbles.removeAt(current)
//            println("Found %23 at marble $marble, remove marble $removeMarble")
            marble + removeMarble
        } else {
            val i = newIndex
            marbles.add(i, marble)
            current = i
            0
        }
    }

    override fun toString(): String =
            marbles.mapIndexed { idx, marble ->
                if (idx == current) "($marble)" else " $marble "
            }.joinToString("")

}

fun play(input: String, factor:Int = 1): Array<Int> {
    val (nbPlayers, nbMarbles) = Regex("(\\d+) players; last marble is worth (\\d+) points").matchEntire(input)
            ?.destructured!!
    println("Starting game : $nbPlayers players and $nbMarbles marbles")
    return play(nbPlayers.toInt(),nbMarbles.toInt() * factor)
}

fun play(nbPlayers: Int, nbMarbles: Int): Array<Int> {
    val start = Date().time
    val circle = Circle(marbles = ArrayList<Int>(nbMarbles).apply { add(0) })
    val scores = Array(nbPlayers) { 0 }
    for (marble in 1..nbMarbles) {
        val player = (marble - 1) % nbPlayers
        scores[player] += circle.add(marble)
//        print("[${player}] ")
//        println(circle)
        if(marble % 100_000 == 0){
            println("Marble : $marble")
        }
    }
    val duration = Date().time - start
    println("Total duration : $duration")
    return scores
}