package adventofcode2018.day13

enum class DirectionEnum {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun from(c: Char): DirectionEnum? =
                when (c) {
                    '^' -> UP
                    'v' -> DOWN
                    '>' -> RIGHT
                    '<' -> LEFT
                    else -> null
                }
    }
}
