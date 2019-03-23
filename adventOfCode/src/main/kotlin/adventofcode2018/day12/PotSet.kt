package adventofcode2018.day12

import java.util.*

class PotSet(var pots: SortedSet<Int> = TreeSet(), val offset: Int = -3) {

    constructor(initialState: String) : this(
            initialState.mapIndexedNotNull { index, c -> if (c == '#') index else null }
                    .toSortedSet()
    )

    operator fun get(index: Int): Boolean = pots.contains(index)

    operator fun set(index: Int, value: Boolean) {
        if (value) {
            pots.add(index)
        } else {
            pots.remove(index)
        }
    }

    val size: Int
        get() = pots.max() ?: 0

    val idxRange: IntRange
        get() = ((pots.min() ?: 0) + offset)..size + 2

    fun isEmpty(): Boolean = size == 0

    override fun toString(): String =
            (offset..size).joinToString("") { if (pots.contains(it)) "#" else "." }
}