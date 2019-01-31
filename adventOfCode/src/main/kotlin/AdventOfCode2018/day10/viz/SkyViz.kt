package AdventOfCode2018.day10.viz

import AdventOfCode2018.day10.common.Sky
import io.data2viz.color.Colors
import io.data2viz.geom.Size
import io.data2viz.scale.Scales
import io.data2viz.viz.viz

fun skyViz(sky: Sky, canvasWidth: Double, canvasHeight: Double) =
        viz {
            val zoom = 150.0

            val scale = Scales.Continuous.linear {
                domain = listOf(sky.minY.toDouble() / zoom, sky.maxY.toDouble() / zoom)
                range = listOf(0.0, canvasHeight)
            }

            val rects = sky.points.map {
                rect {
                    val (px, py) = it.position
                    x = scale(px.toDouble())
                    y = scale(py.toDouble())
                    size = Size(2.0, 2.0)
                    fill = Colors.Web.blue
                }
            }

            val stepText = text {
                x = 50.0
                y = 50.0
                textContent = "Step ${sky.current}"
            }

            animation {
                stepText.textContent = "Step ${sky.current}"
                rects.forEachIndexed { index, rectNode ->
                    val (px, py) = sky.points[index].position
                    rectNode.x = scale(px.toDouble())
                    rectNode.y = scale(py.toDouble())
                }
            }
        }
