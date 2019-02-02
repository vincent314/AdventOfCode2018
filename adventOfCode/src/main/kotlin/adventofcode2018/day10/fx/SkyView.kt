package adventofcode2018.day10.fx

import adventofcode2018.day10.common.Sky
import adventofcode2018.day10.readFile
import adventofcode2018.day10.viz.skyViz
import io.data2viz.viz.JFxVizRenderer
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.control.ScrollBar
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File

class SkyView : View() {
    override val root: BorderPane by fxml()

    private val skyCanvas: Canvas by fxid()

    private val stepScroll: ScrollBar by fxid()

    private val stepLabel: Label by fxid()

    private val startStep = 10000

    val sky = Sky(readFile(File("input", "day10.txt")))
            .also {
                it.setAt(startStep)
            }

    val stepProperty = SimpleIntegerProperty(startStep)

    init {
        JFxVizRenderer(skyCanvas, skyViz(sky, skyCanvas.height))
                .startAnimations()

        stepScroll.valueProperty().bindBidirectional(stepProperty)
        stepLabel.bind(stepProperty)

        stepProperty.onChange { step ->
            sky.setAt(step)
        }
    }
}
