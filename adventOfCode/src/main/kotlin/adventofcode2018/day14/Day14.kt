package adventofcode2018.day14

class ScoreBoard(initList: MutableList<Int>) : MutableList<Int> by initList {
    private val nextRecipesCount: Int = 10

    private val elf1 = Elf(0, this)
    private val elf2 = Elf(1, this)

    internal fun createRecipe() {
        val sum = (get(elf1.position) + get(elf2.position))
        when {
            sum > 9 -> {
                add(1)
                add(sum % 10)
            }
            else -> add(sum)
        }
    }

    private fun moveElves() {
        elf1.move()
        elf2.move()
    }

    fun play(steps: Int = 1) {
        for (i in 1..steps) {
            createRecipe()
            moveElves()
        }
    }

    fun resolve1(input: Int): String {
        play(input + nextRecipesCount)
        return subList(input, input + nextRecipesCount).joinToString("")
    }

    fun resolve2(input: String): Int {
        val nbBefore = input.length
        var step = nbBefore
        play(nbBefore)

        val regex = Regex("""$input\d?""")

        while (!regex.matches(subList(size - nbBefore, size).joinToString(""))) {
            val previousSize = size
            play()
            if (size % 1000 == 0) {
                println(size)
            }
            step += size - previousSize
        }
        return step - 1
    }


    override fun toString(): String {
        return mapIndexed { index, score ->
            when (index) {
                elf1.position -> "($score)"
                elf2.position -> "[$score]"
                else -> " $score "
            }
        }.joinToString("")
    }
}

data class Elf(var position: Int, val scoreBoard: ScoreBoard) {
    fun move() {
        val currentScore = scoreBoard[position]
        position += 1 + currentScore
        position %= scoreBoard.size
    }
}

