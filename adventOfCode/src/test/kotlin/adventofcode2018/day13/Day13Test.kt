package adventofcode2018.day13

import adventofcode2018.day13.DirectionEnum.*
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.io.File


class Day13Test {


    @Test
    fun `should parse simpleinput string`() {
        val input =
                """/----\
                  ||    |
                  ||    |
                  |\----/""".trimMargin()

        Track(input).toString() shouldEqual input
    }

    @Test
    fun `should parse more complex input with carts`() {
        val input =
                """/->-\
                  ||   |  /----\
                  || /-+--+-\  |
                  || | |  | v  |
                  |\-+-/  \-+--/
                  |  \------/""".trimMargin()

        val track = Track(input)
        track.toString() shouldEqual """/->-\
                                       ||   |  /----\
                                       || /-+--+-\  |
                                       || | |  | v  |
                                       |\-+-/  \-+--/
                                       |  \------/""".trimMargin()

        track.carts shouldEqual listOf(
                Cart(RIGHT, Position(2, 0)),
                Cart(DOWN, Position(9, 3)))
    }

    @Test
    fun `should play cart next tick`() {
        val input =
                """/-->\
                  ||   |
                  |\---/""".trimMargin()
        val track = Track(input)

        (1..10).joinToString("\n\n") {
            track.nextTick()
            track.toString()
        }.trimEnd() shouldEqual
                """/---v
                  ||   |
                  |\---/
                  |
                  |/---\
                  ||   v
                  |\---/
                  |
                  |/---\
                  ||   |
                  |\---<
                  |
                  |/---\
                  ||   |
                  |\--</
                  |
                  |/---\
                  ||   |
                  |\-<-/
                  |
                  |/---\
                  ||   |
                  |\<--/
                  |
                  |/---\
                  ||   |
                  |^---/
                  |
                  |/---\
                  |^   |
                  |\---/
                  |
                  |>---\
                  ||   |
                  |\---/
                  |
                  |/>--\
                  ||   |
                  |\---/""".trimMargin()
    }

    @Test
    fun `should navigate through intersections`() {
        val input =
                """|/->-\
                   ||   |  /----\
                   || /-+--+-\  |
                   || | |  | v  |
                   |\-+-/  \-+--/
                   |  \------/""".trimMargin()
        val track = Track(input)
        (1..13).joinToString("\n\n") {
            track.nextTick()
            track.toString()
        } shouldEqual
                """|/-->\
                   ||   |  /----\
                   || /-+--+-\  |
                   || | |  | |  |
                   |\-+-/  \->--/
                   |  \------/
                   |
                   |/---v
                   ||   |  /----\
                   || /-+--+-\  |
                   || | |  | |  |
                   |\-+-/  \-+>-/
                   |  \------/
                   |
                   |/---\
                   ||   v  /----\
                   || /-+--+-\  |
                   || | |  | |  |
                   |\-+-/  \-+->/
                   |  \------/
                   |
                   |/---\
                   ||   |  /----\
                   || /->--+-\  |
                   || | |  | |  |
                   |\-+-/  \-+--^
                   |  \------/
                   |
                   |/---\
                   ||   |  /----\
                   || /-+>-+-\  |
                   || | |  | |  ^
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /----\
                   || /-+->+-\  ^
                   || | |  | |  |
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /----<
                   || /-+-->-\  |
                   || | |  | |  |
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /---<\
                   || /-+--+>\  |
                   || | |  | |  |
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /--<-\
                   || /-+--+-v  |
                   || | |  | |  |
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /-<--\
                   || /-+--+-\  |
                   || | |  | v  |
                   |\-+-/  \-+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /<---\
                   || /-+--+-\  |
                   || | |  | |  |
                   |\-+-/  \-<--/
                   |  \------/
                   |
                   |/---\
                   ||   |  v----\
                   || /-+--+-\  |
                   || | |  | |  |
                   |\-+-/  \<+--/
                   |  \------/
                   |
                   |/---\
                   ||   |  /----\
                   || /-+--v-\  |
                   || | |  | |  |
                   |\-+-/  ^-+--/
                   |  \------/""".trimMargin()
    }

    @Test
    fun `should detect collision`() {
        val input = """|/---\
                              ||   |  /----\
                              || /-+--v-\  |
                              || | |  | |  |
                              |\-+-/  ^-+--/
                              |  \------/""".trimMargin()
        val track = Track(input)
        track.nextTick()
        track.collisionLog shouldEqual listOf(
                Position(x = 7, y = 3),
                Position(x = 7, y = 3)
        )
    }

    @Test
    fun `should resolve part1`() {
        val track = Track(File("../input", "day13.txt"))
        do {
            track.nextTick()
        } while (track.collisionLog.isEmpty())

        track.collisionLog.first().toString() shouldEqual "5,102"
    }

    @Test
    fun `should play until last crash`() {
        val input =
                """/>-<\
                  ||   |
                  || /<+-\
                  || | | v
                  |\>+</ |
                  |  |   ^
                  |  \<->/""".trimMargin()
        val track = Track(input)

        val steps = mutableListOf<String>()

        while (track.carts.size > 1) {
            track.nextTick()
            steps += track.toString()
        }

        steps.joinToString("\n\n") shouldEqual
                """/---\
                  ||   |
                  || v-+-\
                  || | | |
                  |\-+-/ |
                  |  |   |
                  |  ^---^
                  |
                  |/---\
                  ||   |
                  || /-+-\
                  || v | |
                  |\-+-/ |
                  |  ^   ^
                  |  \---/
                  |
                  |/---\
                  ||   |
                  || /-+-\
                  || | | |
                  |\-+-/ ^
                  |  |   |
                  |  \---/""".trimMargin()

        track.carts.map { it.position.toString() } shouldEqual listOf("6,4")
    }

    @Test
    fun `should resolve part2`() {
        val track = Track(File("../input", "day13.txt"))
        while (track.carts.size > 1) {
            track.nextTick()
        }

        track.carts.map { it.position.toString() } shouldEqual listOf("116,54")
    }

}
