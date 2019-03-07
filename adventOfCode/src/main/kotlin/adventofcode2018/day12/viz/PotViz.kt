package adventofcode2018.day12.viz

import adventofcode2018.day12.PotArray
import io.data2viz.color.Colors
import io.data2viz.scale.Scales
import io.data2viz.viz.textAlign
import io.data2viz.viz.viz

fun potViz(potArray: PotArray, width: Double) = viz {
  val scale = Scales.Continuous.linear {
    domain = listOf(0.0, potArray.pots.size.toDouble())
    range = listOf(0.0, width)
  }


  potArray.forEachIndexed { idx, hasPlant ->
    val xOffset = scale(idx)
    val yOffset = 50.0
    group {
      path {
        moveTo(xOffset, yOffset)
        fill = Colors.Web.blueviolet
        lineTo(xOffset + 20, yOffset)
        lineTo(xOffset + 15, yOffset + 20)
        lineTo(xOffset + 5, yOffset + 20)
        closePath()
      }
      text {
        textContent = (idx - potArray.offset).toString()
        x = xOffset + 5
        y = yOffset + 30
      }

      if (hasPlant) {
        line {
          stroke = Colors.Web.green
          x1 = xOffset + 10
          y1 = yOffset
          x2 = xOffset + 10
          y2 = yOffset - 5
        }
        circle {
          fill = Colors.Web.green
          x = xOffset + 10
          y = yOffset - 10
          radius = 5.0
        }
      }
    }
  }
}
