package adventofcode2018.day13.fx

import adventofcode2018.day13.Track
import adventofcode2018.day13.viz.trackViz
import io.data2viz.timer.Timer
import io.data2viz.timer.timer
import io.data2viz.viz.JFxVizRenderer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File

class TrackView : View("My View") {
    override val root: BorderPane by fxml()

    private val trackCanvas: Canvas by fxid()

    private val track = Track(File("./input", "day13.txt"))

    private val t: Timer

    private var pause: Boolean = false

    init {
        t = timer(200.0) {
            JFxVizRenderer(trackCanvas, trackViz(trackCanvas.width, track)).render()
            if(track.carts.size == 1){
                pause = true
            }
            if (!pause) {
                track.nextTick()
            }
        }
    }

    fun onPause() {
        pause = !pause
    }
}
