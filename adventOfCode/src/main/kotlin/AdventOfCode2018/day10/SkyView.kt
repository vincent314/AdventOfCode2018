package AdventOfCode2018.day10

import AdventOfCode2018.day10.viz.skyViz
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import tornadofx.View

class SkyView : View() {
    override val root: BorderPane by fxml()

    private val skyCanvas: Canvas by fxid()

    val skyPresenter:SkyPresenter = SkyPresenter(10_000)
    init {
        JFxVizRenderer(skyCanvas, skyViz(skyPresenter, skyCanvas.width, skyCanvas.height))
                .startAnimations()
    }

    fun onNext() {
        skyPresenter.next()
    }

    fun onPrevious(){
        skyPresenter.previous()
    }
}
