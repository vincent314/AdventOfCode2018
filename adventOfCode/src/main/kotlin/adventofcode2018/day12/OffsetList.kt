package adventofcode2018.day12

class OffsetList(var list: List<Boolean> = listOf(), val offset: Int = 3) {

    constructor(initialState: String, offset: Int = 3) : this(
            initialState
                    .let {
                        (1..offset).joinToString("") { "." } + it
                    }
                    .map { it == '#' },
            offset) {

    }

    val idxRange: IntRange
        get() = -offset until list.size - offset

    operator fun get(idx: Int): Boolean =
            if (idx + offset in 0 until list.size) {
                list[idx + offset]
            } else {
                false
            }

    override fun toString(): String = list.joinToString("") { it.toChar().toString() }


}