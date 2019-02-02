package adventofcode2018.day4

import tornadofx.App
import tornadofx.launch

class Day4App:App(TimeLineView::class)

fun main(args:Array<String>){
    launch<Day4App>(args)
}