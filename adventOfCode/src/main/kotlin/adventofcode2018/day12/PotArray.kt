package adventofcode2018.day12

class PotArray(
        val size: Int = 10,
        val offset: Int = 3,
        val pots: MutableList<Boolean> = ArrayList<Boolean>(size + offset + 2).also {
            for (i in 0 until size + offset + 2) {
                it.add(false)
            }
        },
        val instructions: Map<String, Boolean> = mapOf()
) {

    init {
        require(offset >= 2)
    }

    constructor(
            initialState: String,
            instructions: Map<String, Boolean>,
            offset: Int = 3
    ) : this(
            initialState.length,
            offset,
            initialState
                    .let { (0 until offset).joinToString("") { "." } + it + ".." }
                    .map { '#' == it }
                    .toMutableList(),
            instructions
    )

    operator fun get(index: Int): Boolean = pots[index.translated]

    operator fun set(index: Int, element: Boolean): Boolean = pots.set(index.translated, element)

    fun slice(from: Int, to: Int): List<Boolean> {
        return pots.slice(from.translated..to.translated)
    }

    override fun toString(): String = pots.joinToString("") { it.toChar().toString() }


    fun next(steps: Int = 1) {

        (0 until steps).forEach {
            val previous = pots.toList()
            for (i in 0..(size - 2)) {
                pots[i] = getNextStep(previous, i)
            }
        }
    }

    private fun getNextStep(previous: List<Boolean>, index: Int): Boolean {
        val neighbourhood = getNeighbourhood(index, previous).toPotString()
        return instructions[neighbourhood] ?: false
    }

    fun getNeighbourhood(index: Int, list: List<Boolean> = pots): List<Boolean> {
        val from = index.translated - 2
        val to = index.translated + 2
        return list.slice(from..to)
    }

    private val Int.translated: Int
        get() = this + offset
}

