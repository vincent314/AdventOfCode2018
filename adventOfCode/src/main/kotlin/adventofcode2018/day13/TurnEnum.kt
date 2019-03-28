package adventofcode2018.day13

enum class TurnEnum {
    LEFT, STRAIGHT, RIGHT;

    operator fun inc(): TurnEnum =
            when (this) {
                LEFT -> STRAIGHT
                STRAIGHT -> RIGHT
                RIGHT -> LEFT
            }
}