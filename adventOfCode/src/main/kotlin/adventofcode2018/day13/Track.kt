package adventofcode2018.day13

import java.io.File
import java.util.*

class Track(input: String) {

    val elements: Array<CharArray> = parseElements(input)
    var carts: SortedSet<Cart> = TreeSet(parseCarts(input))
    val collisionLog: MutableList<Position> = mutableListOf()
    val maxWidth: Int
        get() = elements.map { it.size }.max() ?: 0

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
        var cartId = 0
        return input.lineSequence()
                .mapIndexed { y, line ->
                    line.mapIndexedNotNull { x, c ->
                        DirectionEnum.from(c)
                                ?.let { direction ->
                                    Cart(cartId++, direction, Position(x, y))
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
        val collisionCartIds = mutableListOf<Int>()
        carts.forEach { cart ->
            if (collisionCartIds.contains(cart.id)) return@forEach
            cart.nextTick(this)
            carts.filter { it.position == cart.position }
                    .takeIf { it.size > 1 }
                    ?.also {
                        collisionCartIds.addAll(it.map(Cart::id))
                        collisionLog.addAll(it.map(Cart::position))
                    }
        }
        carts.removeIf { it.id in collisionCartIds }
        carts = carts.toSortedSet()
    }
}
