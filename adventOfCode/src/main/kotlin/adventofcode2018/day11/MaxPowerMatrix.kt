package adventofcode2018.day11

class MaxPowerMatrix(private val battery: Battery, private val squareSize: Int = 3) {
    var data: IntArray
    val size: Int = battery.size - squareSize + 1

    init {
        data = IntArray(size * size) { xy ->
            val (x, y) = fromXY(xy)
            getSquareTotalPower(x, y)
        }
    }

    fun getSquareTotalPower(x: Int, y: Int): Int =
            (0 until squareSize * squareSize).map { battery.get(x + it % squareSize, y + it / squareSize) }.sum()

    val maxPowerCoord: Triple<Int, Int, Int>
        get() {
            var maxCoord = 0 to 0
            var maxPower = Int.MIN_VALUE
            for (xy in 0 until size * size) {
                val current = data[xy]
                if (current > maxPower) {
                    val (x, y) = fromXY(xy)
                    maxCoord = x to y
                    maxPower = current
                }
            }
            val (x, y) = maxCoord
            return Triple(x, y, maxPower)
        }

    fun fromXY(xy: Int): Pair<Int, Int> = (xy % size) to (xy / size)
}