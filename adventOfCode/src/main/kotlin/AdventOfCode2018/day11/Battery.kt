package AdventOfCode2018.day11

class Battery(val serialNumber: Long, val size: Int = 300) {

    val data = Array(size) { IntArray(size) }
    val squareSize = 3

    init {
        for (y in 0 until size) {
            for (x in 0 until size) {
                data[y][x] = getPowerLevel(x, y)
            }
        }
    }

    val maxPowerMatrix: Array<IntArray>
        get() {
            return Array(size - 2) { y ->
                IntArray(size - 2) { x ->
                    getSquareTotalPower(x, y)
                }
            }
        }

    fun getPowerLevel(x: Int, y: Int): Int {
        val rackId: Long = x + 10L
        val powerLevel: Long = (rackId * y + serialNumber) * rackId
        val hundredDigit: Long = (powerLevel / 100) - (powerLevel / 1000) * 10
        return hundredDigit.toInt() - 5
    }

    fun get(x: Int, y: Int): Int = data[y][x]

    fun get(xRange: IntRange, yRange: IntRange): Array<IntArray> =
            yRange.map { y ->
                xRange.map { x -> get(x, y) }.toIntArray()
            }.toTypedArray()

    fun getSquareTotalPower(x: Int, y: Int): Int =
            (0 until squareSize * squareSize).map { get(x + it % squareSize, y + it / squareSize) }.sum()

    fun toString(xRange: IntRange, yRange: IntRange): String {
        return yRange.map { y ->
            xRange.map { x -> get(x, y) }
                    .joinToString(" ")
        }.joinToString("\n")
    }
}