package adventofcode2018.day11

class Battery(private val serialNumber: Long, val size: Int = 300) {

    private val data = IntArray(size * size)

    init {
        for (xy in 0 until size * size) {
            val (x, y) = fromXY(xy)
            data[xy] = getPowerLevel(x, y)
        }


    }

    private fun fromXY(xy: Int): Pair<Int, Int> = Pair(xy % size, xy / size)

    private fun toXY(x: Int, y: Int): Int = y * size + x

    fun getPowerLevel(x: Int, y: Int): Int {
        val rackId: Long = x + 10L
        val powerLevel: Long = (rackId * y + serialNumber) * rackId
        val hundredDigit: Long = (powerLevel / 100) - (powerLevel / 1000) * 10
        return hundredDigit.toInt() - 5
    }

    fun get(x: Int, y: Int): Int = get(toXY(x, y))
    fun get(xy: Int) = data[xy]

    fun get(xRange: IntRange, yRange: IntRange): Array<IntArray> =
            yRange.map { y ->
                xRange.map { x -> get(x, y) }.toIntArray()
            }.toTypedArray()


    fun toString(xRange: IntRange, yRange: IntRange): String {
        return yRange.map { y ->
            xRange.map { x -> get(x, y) }
                    .joinToString(" ")
        }.joinToString("\n")
    }
}