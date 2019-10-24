package adventofcode2018.day15


fun main() {
    val map = """#######
                |#.G.E.#
                |#E.G.E#
                |#.G.E.#
                |#######""".trimMargin()

    AreaRenderer(Area(map)).render()
}
