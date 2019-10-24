package adventofcode2018.day15

class Scale(
        from: Double,
        to: Double,
        canvasStart: Double,
        canvasEnd: Double
) {
    private val ratio: Double

    init {
        require(from != to) { "'from' must be different from 'to'" }
        require(canvasStart != canvasEnd) { "'canvasStart' must be different of 'canvasEnd'" }
        ratio = (canvasEnd - canvasStart) / (to - from)
    }

    operator fun get(value: Double): Double = value * ratio

    operator fun get(value: Int): Double = get(value.toDouble())
}
