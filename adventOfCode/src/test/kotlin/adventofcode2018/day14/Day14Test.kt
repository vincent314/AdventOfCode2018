package adventofcode2018.day14

import org.amshove.kluent.shouldEqual
import org.junit.Ignore
import org.junit.Test

class Day14Test {

    @Test
    fun `should create new recipes`() {
        val scoreBoard = ScoreBoard(mutableListOf(3, 7))
        scoreBoard.createRecipe()
        scoreBoard.toList() shouldEqual listOf(3, 7, 1, 0)
    }

    @Test
    fun `should print score board`() {
        val scoreBoard = ScoreBoard(mutableListOf(3, 7))
        scoreBoard.toString() shouldEqual "(3)[7]"
        scoreBoard.play(2)

        scoreBoard.toString() shouldEqual " 3  7  1 [0](1) 0 "
    }

    @Test
    fun `should play 15 times`() {
        val scoreBoard = ScoreBoard(mutableListOf(3, 7))
        scoreBoard.play(15)
        scoreBoard.toString() shouldEqual " 3  7  1  0 [1] 0  1  2 (4) 5  1  5  8  9  1  6  7  7  9  2 "
    }

    @Test
    fun `should resolve part 1`() {
        ScoreBoard(mutableListOf(3, 7)).resolve1(9) shouldEqual "5158916779"
        ScoreBoard(mutableListOf(3, 7)).resolve1(5) shouldEqual "0124515891"
        ScoreBoard(mutableListOf(3, 7)).resolve1(18) shouldEqual "9251071085"
        ScoreBoard(mutableListOf(3, 7)).resolve1(2018) shouldEqual "5941429882"
        ScoreBoard(mutableListOf(3, 7)).resolve1(380621) shouldEqual "6985103122"
    }

    @Test
    fun `should resolve part 2`() {
        ScoreBoard(mutableListOf(3, 7)).resolve2("51589") shouldEqual 9
        ScoreBoard(mutableListOf(3, 7)).resolve2("01245") shouldEqual 5
        ScoreBoard(mutableListOf(3, 7)).resolve2("92510") shouldEqual 18
        ScoreBoard(mutableListOf(3, 7)).resolve2("59414") shouldEqual 2018
        ScoreBoard(mutableListOf(3,7)).resolve2("380621") shouldEqual 2018
    }
}
