package adventofcode2018.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class AreaTest {
    @Test
    fun testAreatoString() {
        val map = """#######
                |#.G.E.#
                |#E.G.E#
                |#.G.E.#
                |#######
                |""".trimMargin()
        val area = Area(map)
        assertEquals(map, area.toString())
    }
}
