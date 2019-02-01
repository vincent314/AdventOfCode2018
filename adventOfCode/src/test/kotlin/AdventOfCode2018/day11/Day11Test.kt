package AdventOfCode2018.day11

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test

class Day11Test {

    @Test
    fun `should init a cell`() {
        Battery(8)
                .getPowerLevel(3, 5) shouldBe 4
        Battery(57)
                .getPowerLevel(122, 79) shouldBe -5
        Battery(39)
                .getPowerLevel(217, 196) shouldBe 0
        Battery(71)
                .getPowerLevel(101, 153) shouldBe 4
    }

    @Test
    fun `should display square values`() {
        Battery(18)
                .toString(33..35, 45..47) shouldEqual """
                    |4 4 4
                    |3 3 4
                    |1 2 4""".trimMargin()
        Battery(42)
                .toString(21..23, 61..63) shouldEqual """
                    |4 3 3
                    |3 3 4
                    |3 3 4""".trimMargin()
    }


    @Test
    fun `should get square power`() {
        Battery(18)
                .getSquareTotalPower(33, 45) shouldBe 29
        Battery(42)
                .getSquareTotalPower(21, 61) shouldBe 30
    }


    @Test
    fun `should get battery max power`(){
        Battery(18).maxPowerCoord shouldEqual (33 to 45)
        Battery(42).maxPowerCoord shouldEqual (21 to 61)
    }

    @Test
    fun `should resolve part1`(){
        Battery(7347).maxPowerCoord shouldEqual (243 to 17)
    }
}