package AdventOfCode2018.day10.fx

import AdventOfCode2018.day10.common.Sky
import AdventOfCode2018.day10.readFile
import AdventOfCode2018.day10.viz.skyViz
import io.data2viz.viz.JFxVizRenderer
import javafx.event.ActionEvent
import javafx.scene.canvas.Canvas
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.View
import java.io.File

class SkyView : View() {
    override val root: BorderPane by fxml()

    private val skyCanvas: Canvas by fxid()

    val sky = Sky(readFile(File("input", "day10.txt")))

    init {
        JFxVizRenderer(skyCanvas, skyViz(sky, skyCanvas.width, skyCanvas.height))
                .startAnimations()
    }

    fun onNext() {
        sky.next()
    }

    fun onPrevious() {
        sky.previous()
    }

    fun onStepInputChange(event: ActionEvent) {
        if(event.source is TextField){
            (event.source as TextField).text.toIntOrNull()
                    ?.also { sky.setAt(it) }
        }
    }

}
