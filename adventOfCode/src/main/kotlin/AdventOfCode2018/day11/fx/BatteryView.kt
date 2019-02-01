package AdventOfCode2018.day11.fx

import AdventOfCode2018.day11.Battery
import AdventOfCode2018.day11.viz.batteryViz
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import tornadofx.*

class BatteryView: View() {
    override val root: BorderPane by fxml()

    val batteryCanvas: Canvas by fxid()

    init {
        val battery = Battery(7347)

        JFxVizRenderer(batteryCanvas, batteryViz(battery, batteryCanvas.width, batteryCanvas.height))
                .render()
    }
}