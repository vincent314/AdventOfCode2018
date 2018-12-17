package AdventOfCode2018.day3

import java.io.File

const val MAX_WIDTH = 1000
const val MAX_HEIGHT = 1000

data class Claim(
        val id: Int,
        val positionX: Int,
        val positionY: Int,
        val width: Int,
        val height: Int
) {
    companion object {
        fun from(str: String): Claim = Regex("""#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""")
                .matchEntire(str)
                ?.let {
                    with(it.groupValues) {
                        Claim(get(1).toInt(), get(2).toInt(), get(3).toInt(), get(4).toInt(), get(5).toInt())
                    }
                }
                ?: throw Exception("Invalid data string $str")
    }
}

class Fabric(maxWidth: Int = MAX_WIDTH, maxHeight: Int = MAX_HEIGHT) {

    private val matrix = Array(maxHeight) { Array(maxWidth) { "" } }
    val nonOverlapIds = mutableSetOf<String>()

    override fun toString(): String {
        return matrix.joinToString("\n") { values ->
            values.joinToString("") { value ->
                when {
                    value.isEmpty() -> "."
                    value == "X" -> "X"
                    else -> "#"
                }
            }
        }
    }

    fun set(x: Int, y: Int, value: String) {
        val previousValue = matrix[y][x]
        if (previousValue.isNotBlank()) {
            matrix[y][x] = "X"
            nonOverlapIds.remove(previousValue)
            nonOverlapIds.remove(value)
        } else {
            matrix[y][x] = value
        }
    }

    fun set(claim: Claim) {
        with(claim) {
            (positionX until positionX + width).forEach { x ->
                (positionY until positionY + height).forEach { y ->
                    set(x, y, id.toString())
                }
            }
        }
    }

    fun countOverlaps(): Int = matrix.flatten().count { it == "X" }

    fun readFile(file: File = File("input", "day3.txt")) {
        file.readLines().forEach {
            val claim = Claim.from(it)
            nonOverlapIds.add(claim.id.toString())
            set(claim)
        }
    }
}