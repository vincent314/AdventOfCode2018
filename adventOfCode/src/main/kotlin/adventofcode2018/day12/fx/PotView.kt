package adventofcode2018.day12.fx

import adventofcode2018.day12.PotArray
import adventofcode2018.day12.readFile
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import tornadofx.*
import java.io.File

class PotView : View("My View") {
    override val root: BorderPane by fxml()

    private val potCanvas: Canvas by fxid()
    private val potArray: PotArray = readFile(File("./input", "day12-example.txt"))
    private val pixelWriter = potCanvas.graphicsContext2D.pixelWriter
    init {
        for (y in 0..600) {
            drawLine(y)
            potArray.next()
        }
    }

    fun drawLine(y:Int) {
        potArray.pots.list.forEachIndexed { idx, value ->
            if(value) {
                pixelWriter.setColor(idx, y, Color.BLACK)
            }
        }
    }

}
