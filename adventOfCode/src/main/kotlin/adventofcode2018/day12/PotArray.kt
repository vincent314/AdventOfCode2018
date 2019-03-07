package adventofcode2018.day12

class PotArray(
        override val size: Int = 10,
        val offset: Int = 3,
        val pots: MutableList<Boolean> = ArrayList<Boolean>(size + offset + 2).also {
            for (i in 0 until size + offset + 2) {
                it.add(false)
            }
        },
        val instructions: Map<String, Char> = mapOf()
) : MutableList<Boolean> by pots {

    init {
        require(offset >= 2)
    }

    constructor(
            initialState: String,
            instructions: Map<String, Char>,
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

    override fun get(index: Int): Boolean = pots[index + offset]

    override fun set(index: Int, element: Boolean): Boolean = pots.set(index + offset, element)

    fun slice(from: Int, to: Int): List<Boolean> {
        return pots.slice(from + offset..to + offset)
    }

    override fun toString(): String = joinToString("") { it.toChar().toString() }


    fun next(steps: Int = 1) {

        (0 until steps).forEach {
            val previous = pots.toList()
            for (i in 2..(pots.size-2)) {
                pots[i] = getNextStep(previous, i)
            }
        }
    }

    fun getNextStep(previous: List<Boolean>, index: Int): Boolean {
        val neighbourhood = previous.getNeighbourhood(index).toPotString()
        return instructions[neighbourhood] == '#'
    }
}

