package AdventOfCode2018.day10.fx

import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch

class Day10App : App(SkyView::class) {
  override fun start(stage: Stage) {
    super.start(stage)
  }
}

fun main() {
  launch<Day10App>()
}
