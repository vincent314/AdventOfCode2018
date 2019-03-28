package adventofcode2018.day13

import java.io.File

class Track(input: String) {

    private val elements: Array<CharArray> = parseElements(input)
    val carts: List<Cart> = parseCarts(input)

    constructor(file: File) : this(file.readText())

    operator fun get(x: Int, y: Int): Char = elements[y][x]
    operator fun get(position: Position): Char = get(position.x, position.y)

    override fun toString(): String = elements
            .mapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    getCartAt(Position(x, y))?.toChar() ?: c
                }
            }
            .joinToString("\n") { it.joinToString("") }

    private fun parseElements(input: String): Array<CharArray> {
        return input.lines()
                .map { line ->
                    line.map { c ->
                        when (c) {
                            '^', 'v' -> '|'
                            '>', '<' -> '-'
                            else -> c
                        }
                    }.toCharArray()
                }.toTypedArray()
    }

    private fun parseCarts(input: String): List<Cart> {
        return input.lineSequence()
                .mapIndexed { y, line ->
                    line.mapIndexedNotNull { x, c ->
                        DirectionEnum.from(c)
                                ?.let { direction ->
                                    Cart(direction, Position(x, y))
                                }
                    }
                }
                .flatten()
                .toList()
    }

    private fun getCartAt(position: Position): Cart? {
        return carts.find { it.position == position }
    }

    fun nextTick() {
        carts.forEach { cart ->
            cart.nextTick(this)
        }
    }

    val collisions: List<Cart>
        get() {
            return carts.groupBy(Cart::position)
                    .filterValues { it.size > 1 }
                    .values
                    .flatten()
        }
}
