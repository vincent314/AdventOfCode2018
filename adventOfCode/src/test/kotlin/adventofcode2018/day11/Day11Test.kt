package adventofcode2018.day11

import adventofcode2018.Solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day11Test {
    val solution = Solutions.readFile().day11

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
        MaxPowerMatrix(Battery(18))
                .getSquareTotalPower(33, 45) shouldBe 29
        MaxPowerMatrix(Battery(42))
                .getSquareTotalPower(21, 61) shouldBe 30
    }


    @Test
    fun `should get battery max power`() {
        MaxPowerMatrix(Battery(18)).maxPowerCoord shouldEqual Triple(33, 45, 29)
        MaxPowerMatrix(Battery(42)).maxPowerCoord shouldEqual Triple(21, 61, 30)
    }

    @Test
    fun `should resolve part1`() {
        val serialNumber = File("../input", "day11.txt").readText().toLong()
        MaxPowerMatrix(Battery(serialNumber)).maxPowerCoord.toString() shouldEqual solution.part1
    }
}