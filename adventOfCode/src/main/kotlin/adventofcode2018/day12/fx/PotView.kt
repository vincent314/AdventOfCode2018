package adventofcode2018.day12.fx

import adventofcode2018.day12.PotArray
import adventofcode2018.day12.readFile
import adventofcode2018.day12.viz.potViz
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File

class PotView : View("My View") {
  override val root: BorderPane by fxml()

  private val potCanvas: Canvas by fxid()
  private val potArray: PotArray = readFile(File("./input", "day12-example.txt"))
  private val renderer:JFxVizRenderer

  init {
    renderer = JFxVizRenderer(potCanvas, potViz(potArray, potCanvas.width))
    renderer.render()
  }

  fun doNext(){
    potArray.next()
    renderer.render()
  }
}
