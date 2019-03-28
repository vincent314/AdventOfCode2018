package adventofcode2018.day13

import adventofcode2018.day13.DirectionEnum.*

data class Cart(var direction: DirectionEnum, var position: Position, var turn: TurnEnum = TurnEnum.LEFT) {
    fun toChar(): Char = when (direction) {
        UP -> '^'
        DOWN -> 'v'
        RIGHT -> '>'
        LEFT -> '<'
    }

    fun nextTick(track: Track) {
        val nextPosition = position.next(direction)
        val nextElement = track[nextPosition]
        direction = when {
            nextElement == '/' && direction == UP -> RIGHT
            nextElement == '/' && direction == LEFT -> DOWN
            nextElement == '/' && direction == RIGHT -> UP
            nextElement == '/' && direction == DOWN -> LEFT
            nextElement == '\\' && direction == UP -> LEFT
            nextElement == '\\' && direction == LEFT -> UP
            nextElement == '\\' && direction == RIGHT -> DOWN
            nextElement == '\\' && direction == DOWN -> RIGHT
            nextElement == '+' -> doTurn()
            else -> direction
        }
        position = nextPosition
    }

    fun doTurn(): DirectionEnum {
         val nextDirection = when (direction) {
            UP -> when (turn) {
                TurnEnum.LEFT -> LEFT
                TurnEnum.STRAIGHT -> UP
                TurnEnum.RIGHT -> RIGHT
            }
            RIGHT -> when (turn) {
                TurnEnum.LEFT -> UP
                TurnEnum.STRAIGHT -> RIGHT
                TurnEnum.RIGHT -> DOWN
            }
            DOWN -> when (turn) {
                TurnEnum.LEFT -> RIGHT
                TurnEnum.STRAIGHT -> DOWN
                TurnEnum.RIGHT -> LEFT
            }
            LEFT -> when (turn) {
                TurnEnum.LEFT -> DOWN
                TurnEnum.STRAIGHT -> LEFT
                TurnEnum.RIGHT -> UP
            }
        }
        turn++
        return nextDirection
    }
}
