package AdventOfCode2018.day11.fx

import AdventOfCode2018.day11.Battery
import AdventOfCode2018.day11.viz.batteryViz
import AdventOfCode2018.day11.viz.totalSquareViz
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import tornadofx.*

class BatteryView : View() {
    override val root: BorderPane by fxml()

    val batteryCanvas: Canvas by fxid()

    val totalSquareCanvas: Canvas by fxid()

    init {
        val battery = Battery(7347)

        JFxVizRenderer(batteryCanvas, batteryViz(battery, batteryCanvas.width, batteryCanvas.height))
                .render()

        JFxVizRenderer(totalSquareCanvas, totalSquareViz(battery, totalSquareCanvas.width, totalSquareCanvas.height))
                .render()
    }
}