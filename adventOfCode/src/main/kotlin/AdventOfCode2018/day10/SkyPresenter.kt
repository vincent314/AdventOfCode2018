package AdventOfCode2018.day10

import java.io.File

class SkyPresenter(startAt:Int = 0) {
  val sky:Sky = Sky(readFile(File("./input", "day10.txt")))
  var current:Int = startAt

  init {
    (0 until startAt).forEach { sky.next() }
  }

  fun next(){
    sky.next()
    current++
  }

  fun previous(){
    sky.previous()
    current--
  }
}
