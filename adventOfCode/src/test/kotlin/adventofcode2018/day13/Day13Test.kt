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
        track.collisions shouldEqual listOf(
                Cart(DOWN, Position(x = 7, y = 3)),
                Cart(UP, Position(x = 7, y = 3))
        )
    }

    @Test
    fun `should resolve part1`(){
        val track = Track(File("../input","day13.txt"))
        do {
            track.nextTick()
        } while(track.collisions.isEmpty())

        track.collisions.first().position.toString() shouldEqual "5,102"
    }

}
