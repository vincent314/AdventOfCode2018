package adventofcode2018.day11.fx

import adventofcode2018.day11.Battery
import adventofcode2018.day11.viz.batteryViz
import adventofcode2018.day11.viz.totalSquareViz
import io.data2viz.viz.JFxVizRenderer
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.control.ScrollBar
import javafx.scene.layout.BorderPane
import tornadofx.*

class BatteryView : View() {
    override val root: BorderPane by fxml()

    val batteryCanvas: Canvas by fxid()

    val totalSquareCanvas: Canvas by fxid()

    val levelScroll: ScrollBar by fxid()

    val levelProperty = SimpleIntegerProperty(3)

    val levelLabel: Label by fxid()

    init {
        val battery = Battery(7347)

        levelScroll.valueProperty().bindBidirectional(levelProperty)
        levelLabel.bind(levelProperty)

        JFxVizRenderer(batteryCanvas, batteryViz(battery, batteryCanvas.width))
                .render()

        JFxVizRenderer(totalSquareCanvas, totalSquareViz(battery, totalSquareCanvas.width, levelProperty.get()))
                .render()

        levelProperty.onChange { level ->
            JFxVizRenderer(totalSquareCanvas, totalSquareViz(battery, totalSquareCanvas.width, level))
                    .render()
        }
    }
}