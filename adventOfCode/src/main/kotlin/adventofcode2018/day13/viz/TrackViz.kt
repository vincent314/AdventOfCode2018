package adventofcode2018.day13.viz

import adventofcode2018.day13.Track
import io.data2viz.color.Colors
import io.data2viz.scale.Scales
import io.data2viz.viz.viz

fun trackViz(width: Double, track: Track) = viz {

    val scale = Scales.Continuous.linear {
        domain = listOf(0.0, track.maxWidth.toDouble())
        range = listOf(0.0, width)
    }

    fun vline(x: Int, y: Int) = line {
        stroke = Colors.Web.black
        x1 = scale(x + 0.5)
        x2 = x1
        y1 = scale(y)
        y2 = scale(y + 1)
    }

    fun hline(x: Int, y: Int) = line {
        stroke = Colors.Web.black
        x1 = scale(x)
        x2 = scale(x + 1)
        y1 = scale(y + 0.5)
        y2 = y1
    }

    fun diag1(x: Int, y: Int) = line {
        stroke = Colors.Web.black
        x1 = scale(x + 0.25)
        x2 = scale(x + 0.75)
        y1 = scale(y + 0.25)
        y2 = scale(y + 0.75)
    }

    fun diag2(x: Int, y: Int) = line {
        stroke = Colors.Web.black
        x1 = scale(x + 0.25)
        x2 = scale(x + 0.75)
        y1 = scale(y + 0.75)
        y2 = scale(y + 0.25)
    }

    track.elements.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            when (c) {
                '|' -> vline(x, y)
                '-' -> hline(x, y)
                '+' -> {
                    vline(x, y); hline(x, y)
                }
                '/' -> diag2(x, y)
                '\\' -> diag1(x, y)
                else -> {
                }
            }
        }
    }

    track.carts.map { it.position }
            .forEach { (x, y) ->
                group {
                    circle {
                        stroke = Colors.Web.blueviolet
                        fill = Colors.Web.blue
                        this.x = scale(x.toDouble() + 0.5)
                        this.y = scale(y.toDouble() + 0.5)
                        radius = scale(0.5)
                    }
                }
            }
    track.collisionLog.forEach { (x, y) ->
        circle {
            fill = Colors.Web.red
            this.x = scale(x + 0.5)
            this.y = scale(y + 0.5)
            radius = scale(1.0)
        }
    }

}