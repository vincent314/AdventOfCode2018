package AdventOfCode2018.day5

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test

class Day5Test {

    @Test
    fun testCombine(){
        isCombine('a','A') shouldBe true
        isCombine('a','a') shouldBe false
        isCombine('a','B') shouldBe false
    }

    @Test
    fun testProcess(){
        process("dabAcCaCBAcCcaDA") shouldEqual "dabCBAcaDA"
    }

    @Test
    fun testResolvePart1(){
        resolvePart1().count() shouldBe 9288
    }
}