package adventofcode2018.day12

import java.util.*
import kotlin.system.measureTimeMillis

data class PotArray(
        var pots: PotSet = PotSet(),
        val instructions: Map<String, Boolean> = mapOf()
) {

    constructor(
            initialState: String,
            instructions: Map<String, Boolean> = mapOf()) : this(
            pots = PotSet(initialState),
            instructions = instructions
    )

    override fun toString(): String = pots.toString()

    operator fun get(idx: Int): Boolean = pots[idx]

    fun next(nb: Int = 1) {
        for (loop in 1..nb) {
            val target = PotSet()
            for (idx in pots.idxRange) {
                val newValue = instructions[getNeighborhood(idx)] ?: false
                target[idx] = newValue
            }
            pots = target
        }
    }


    private fun compact(list: MutableList<Boolean>): MutableList<Boolean> {
        (0 until list.size).reversed().takeWhile { !list[it] }
                .forEach { list.removeAt(it) }
        return list
    }

    fun getNeighborhood(idx: Int): String {
        return (idx - 2..idx + 2).joinToString("") { this[it].toChar().toString() }
    }

    val signature: Long
        get() = pots.pots.map(Int::toLong).sum()
}