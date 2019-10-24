package adventofcode2018.day15

import adventofcode2018.day15.SquareType.*
import kotlinx.html.dom.create
import kotlinx.html.js.canvas
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.math.PI

actual class AreaRenderer actual constructor(val area: Area) {
    private val w = 300.0
    private val h = 300.0
    private val scaleX = Scale(0.0, area.matrix[0].size.toDouble(), 0.0, w)
    private val scaleY = Scale(0.0, area.matrix.size.toDouble(), 0.0, h)
    private val context: CanvasRenderingContext2D
    private val canvas: HTMLCanvasElement

    init {
        canvas = document.create.canvas {
            width = w.toInt().toString()
            height = h.toInt().toString()

        }

        context = canvas.getContext("2d") as CanvasRenderingContext2D
    }

    actual fun render() {
        with(context) {
            strokeRect(0.0, 0.0, w, h)
        }
        area.matrix.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                when (cell) {
                    WALL -> wall(x, y)
                    ELF -> elf(x, y)
                    GOBELIN -> gobelin(x, y)
                    else -> {
                    }
                }
            }
        }
        document.getElementById("root")!!.appendChild(canvas)
    }

    private fun wall(x: Int, y: Int) {
        with(context) {
            fillStyle = "gray"
            fillRect(scaleX[x], scaleY[y], scaleX[x + 1], scaleY[y + 1])
        }
    }

    private fun circle(x: Int, y: Int, fillStyle: String) {
        with(context) {
            beginPath()
            arc(scaleX[x + 0.5], scaleY[y + 0.5], scaleX[0.5], 0.0, 2 * PI)
            this.fillStyle = fillStyle
            fill()
            stroke()
        }
    }

    private fun elf(x: Int, y: Int) {
        circle(x, y, "green")
    }

    private fun gobelin(x: Int, y: Int) {
        circle(x, y, "red")
    }
}
