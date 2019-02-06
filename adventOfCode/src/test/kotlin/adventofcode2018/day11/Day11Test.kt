package adventofcode2018.day11

import adventofcode2018.Solutions
import adventofcode2018.solutions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File

class Day11Test {
    val solution = solutions().day11

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
        MaxPowerMatrix(Battery(18)).maxPowerCell shouldEqual MaxPowerCell(33, 45, 29)
        MaxPowerMatrix(Battery(42)).maxPowerCell shouldEqual MaxPowerCell(21, 61, 30)
    }

    @Test
    fun `should resolve part1`() {
        val serialNumber = File("../input", "day11.txt").readText().toLong()
        MaxPowerMatrix(Battery(serialNumber)).maxPowerCell.toString() shouldEqual solution.part1
    }

    @Test
    fun `should find max power with level`() {
        MaxPowerMatrix.getMaxPowerSquare(18) shouldEqual MaxPowerCell(90, 269, 113, 16)
        MaxPowerMatrix.getMaxPowerSquare(42) shouldEqual MaxPowerCell(232, 251, 119, 12)
    }

    @Test
    fun `should resolve part2`() {
        val serialNumber = File("../input", "day11.txt").readText().toLong()
        MaxPowerMatrix.getMaxPowerSquare(serialNumber) shouldEqual MaxPowerCell(233, 228, 91, 12)
    }
}