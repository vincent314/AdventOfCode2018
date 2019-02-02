package adventofcode2018.day11

import kotlin.system.measureTimeMillis

data class MaxPowerCell(
        val x: Int,
        val y: Int,
        val power: Int,
        val level: Int = 3
)

class MaxPowerMatrix(private val battery: Battery, private val squareSize: Int = 3) {
    companion object {

        fun getMaxPowerSquare(serialNumber: Long): MaxPowerCell? {
            val battery = Battery(serialNumber)
            return (1 until battery.size)
                    .asSequence()
                    .map { level ->
                        MaxPowerMatrix(battery, level).maxPowerCell
                    }
//                    .onEach { cell -> println(cell) }
                    .maxBy { it.power }
        }

    }

    var data: IntArray = IntArray(0)
    val size: Int = battery.size - squareSize + 1

    init {
        val time = measureTimeMillis {
            data = IntArray(size * size) { xy ->
                val (x, y) = fromXY(xy)
                getSquareTotalPower(x, y)
            }
        }
        println("Get max power for level $squareSize = $time")
    }

    //    fun getSquareTotalPower(x: Int, y: Int): Int =
//            (0 until squareSize * squareSize).map { battery.get(x + it % squareSize, y + it / squareSize) }.sum()
    fun getSquareTotalPower(x: Int, y: Int):Int {
        var total = 0
        for(cy in y until y+squareSize){
            for(cx in x until x+squareSize){
                total += battery.get(cx,cy)
            }
        }
        return total
    }

    val maxPowerCell: MaxPowerCell
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
            return MaxPowerCell(x, y, maxPower, squareSize)
        }

    fun fromXY(xy: Int): Pair<Int, Int> = (xy % size) to (xy / size)
}