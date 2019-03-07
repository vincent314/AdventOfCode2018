package adventofcode2018.day12.fx

import adventofcode2018.day12.PotArray
import adventofcode2018.day12.readFile
import adventofcode2018.day12.viz.potViz
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File

class PotView : View("My View") {
  override val root: BorderPane by fxml()

  val potCanvas: Canvas by fxid()
  val nextButton: Button by fxid()
  val potArray: PotArray
  val renderer:JFxVizRenderer

  init {
    potArray = readFile(File("./input", "day12-example.txt"))
    renderer = JFxVizRenderer(potCanvas, potViz(potArray, potCanvas.width))
    renderer.render()
  }

  fun doNext(){
    potArray.next()
    renderer.render()
  }
}
