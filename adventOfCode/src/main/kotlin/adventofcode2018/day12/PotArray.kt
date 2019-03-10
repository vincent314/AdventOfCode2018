package adventofcode2018.day12

import java.util.*
import kotlin.system.measureTimeMillis

data class PotArray(
        var pots: OffsetList = OffsetList(),
        val instructions: Map<String, Boolean> = mapOf()
) {

    constructor(
            initialState: String,
            instructions: Map<String, Boolean> = mapOf()) : this(
            pots = OffsetList(initialState),
            instructions = instructions
    )

    override fun toString(): String = pots.toString()

    operator fun get(idx: Int): Boolean = pots[idx]

    fun next(nb: Long = 1) {
        var time = 0L
        for (loop in 1..nb) {
            if (loop % 1000 == 0L) {
                println("1000 iterations time = $time ms")
                time = 0L
            }

            time += measureTimeMillis {
                val idxRange = pots.idxRange
                val target = Vector<Boolean>(idxRange.count()+3)
                for (idx in idxRange.start until idxRange.endInclusive+2) {
                    val newValue = instructions[getNeighborhood(idx)] ?: false
                    target += newValue
                }
                pots = OffsetList(compact(target.toMutableList()))
            }
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
        get() = pots.idxRange.map { if (pots[it]) it.toLong() else 0L }.sum()
}