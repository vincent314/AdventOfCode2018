package AdventOfCode2018.day11.viz

import AdventOfCode2018.day11.Battery
import io.data2viz.color.Colors
import io.data2viz.geom.Size
import io.data2viz.scale.Scales
import io.data2viz.scale.ScalesChromatic
import io.data2viz.viz.viz

fun totalSquareViz(battery: Battery, canvasWidth: Double, canvasHeight: Double) = viz {
    val maxPowerMatrix = battery.maxPowerMatrix

    val scale = Scales.Continuous.linear {
        domain = listOf(0.0, battery.maxPowerMatrixSize.toDouble())
        range = listOf(0.0, canvasWidth)
    }

    val gradient = ScalesChromatic.Continuous.linearRGB {
        domain = listOf(-81.0, 81.0)
        range = listOf(Colors.Web.blue, Colors.Web.red)
    }

    maxPowerMatrix.forEachIndexed { xy, power ->
        rect {
            x = scale(xy % battery.maxPowerMatrixSize)
            y = scale(xy / battery.maxPowerMatrixSize)
            size = Size(scale(1.0), scale(1.0))
            fill = gradient(power)
        }
    }
}