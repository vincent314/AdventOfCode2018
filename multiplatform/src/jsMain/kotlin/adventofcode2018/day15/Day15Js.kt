package adventofcode2018.day15

import kotlinx.html.dom.create
import kotlinx.html.js.canvas
import org.w3c.dom.CanvasRenderingContext2D
import kotlin.browser.document

actual fun render() {
    val map = """#######
                |#.G.E.#
                |#E.G.E#
                |#.G.E.#
                |#######
                |""".trimMargin()

    println(Area(map).toString())
    val canvas = document.create.canvas {
        width = "300"
        height = "300"

    }

    with(canvas.getContext("2d") as CanvasRenderingContext2D) {
        moveTo(0.0, 0.0)
        lineTo(300.0,300.0)
        stroke()
    }
    document.getElementById("root")!!.appendChild(canvas)
}
