package adventofcode2018.day14


class ScoreBoard(initList: MutableList<Int>) : MutableList<Int> by initList {
    private val nextRecipesCount: Int = 10

    val elves: List<Elf> = listOf(Elf(0, this), Elf(1, this))

    internal fun createRecipe() {
        val newRecipes = elves
                .map {
                    get(it.position)
                }
                .sum()
                .toString()
                .map { it.toString().toInt() }

        addAll(newRecipes)
    }

    private fun moveElves() {
        elves.forEach(Elf::move)
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

    override fun toString(): String {
        val (elf1, elf2) = elves
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

