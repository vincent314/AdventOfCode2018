package adventofcode2018.day12

import java.lang.IndexOutOfBoundsException

data class PotArray(
        private val initialCapacity: Int = 30,
        val pots: MutableList<Boolean> = ArrayList<Boolean>(initialCapacity).apply { fill(initialCapacity) },
        val instructions: Map<String, Boolean> = mapOf(),
        val leftPots: MutableList<Boolean> = ArrayList<Boolean>(3).apply { fill(3) }
) {

    constructor(
            initialState: String,
            instructions: Map<String, Boolean>) : this(
            pots = initialState.map { it == '#' }.toMutableList(),
            instructions = instructions
    )

    override fun toString(): String =
            leftPots
                    .slice(1 until leftPots.size)
                    .reversed()
                    .joinToString("") { it.toChar().toString() } +
                    pots.joinToString("") { it.toChar().toString() }

    operator fun get(idx: Int): Boolean =
            when {
                -idx in (1 until leftPots.size) -> leftPots[-idx]
                idx in (0 until pots.size) -> pots[idx]
                else -> false
            }

    operator fun set(idx: Int, value: Boolean) {
        when {
            -idx in (1 until leftPots.size) -> leftPots[-idx] = value
            idx in (0 until pots.size) -> pots[idx] = value
            idx >= pots.size -> {
                pots.fill(idx)
                pots[idx] = value
            }
            else -> throw IndexOutOfBoundsException(idx.toString())
        }
    }

    fun next(nb: Int = 1) {
        (1..nb).forEach { _ ->
            val previous = this.copy(pots = ArrayList(pots), leftPots = ArrayList(leftPots))
            (-2 until pots.size + 1).forEach { idx ->
                val newValue = instructions[previous.getNeighborhood(idx)] ?: false
                this[idx] = newValue
            }

            compact()
        }
    }

    private fun compact() {
        (0 until pots.size).reversed().takeWhile { !pots[it] }
                .forEach { pots.removeAt(it) }
    }

    fun getNeighborhood(idx: Int): String {
        return (idx - 2..idx + 2).joinToString("") { this[it].toChar().toString() }
    }

    val signature: Int
        get() {
            return pots.mapIndexed { idx, value -> if (value) idx else 0 }.sum() +
                    leftPots.mapIndexed { idx, value -> if (value) (-idx) else 0 }.sum()
        }
}