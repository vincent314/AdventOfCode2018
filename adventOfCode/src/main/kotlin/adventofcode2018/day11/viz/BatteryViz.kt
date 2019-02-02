package adventofcode2018.day11.viz

import adventofcode2018.day11.Battery
import io.data2viz.color.Colors
import io.data2viz.geom.Size
import io.data2viz.scale.Scales
import io.data2viz.scale.ScalesChromatic
import io.data2viz.viz.viz

fun batteryViz(battery: Battery, canvasWidth: Double) = viz {
    val scale = Scales.Continuous.linear {
        domain = listOf(0.0, battery.size.toDouble())
        range = listOf(0.0, canvasWidth)
    }

    val gradient = ScalesChromatic.Continuous.linearRGB {
        domain = listOf(-5.0, 4.0)
        range = listOf(Colors.Web.blue, Colors.Web.red)
    }


    for (py in 0 until battery.size) {
        for (px in 0 until battery.size) {
            val powerLevel = battery.get(px, py)
            rect {
                x = scale(px.toDouble())
                y = scale(py.toDouble())
                size = Size(scale(1.0), scale(1.0))
                fill = gradient(powerLevel)
            }
        }
    }
}