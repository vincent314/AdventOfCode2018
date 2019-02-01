package AdventOfCode2018.day11

class Battery(val serialNumber: Long, val size: Int = 300) {

    val data = IntArray(size * size)
    val squareSize = 3
    val maxPowerMatrix: IntArray
    val maxPowerMatrixSize: Int = size - squareSize + 1


    init {
        for (xy in 0 until size * size) {
            val (x, y) = fromXY(xy)
            data[xy] = getPowerLevel(x, y)
        }

        maxPowerMatrix = IntArray(maxPowerMatrixSize * maxPowerMatrixSize) {
            val (x, y) = fromXY(it)
            getSquareTotalPower(x, y)
        }
    }

    val maxPowerCoord: Pair<Int, Int>
        get() {
            var maxCoord = 0 to 0
            var maxPower = Int.MIN_VALUE
            for (xy in 0 until maxPowerMatrix.size) {
                val current = maxPowerMatrix[xy]
                if (current > maxPower) {
                    val (x, y) = fromXY(xy)
                    maxCoord = x to y
                    maxPower = current
                }
            }
            return maxCoord
        }

    fun fromXY(xy: Int): Pair<Int, Int> = Pair(xy % size, xy / size)

    fun toXY(x: Int, y: Int): Int = y * size + x

    fun getPowerLevel(x: Int, y: Int): Int {
        val rackId: Long = x + 10L
        val powerLevel: Long = (rackId * y + serialNumber) * rackId
        val hundredDigit: Long = (powerLevel / 100) - (powerLevel / 1000) * 10
        return hundredDigit.toInt() - 5
    }

    fun get(x: Int, y: Int): Int = data[toXY(x, y)]

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