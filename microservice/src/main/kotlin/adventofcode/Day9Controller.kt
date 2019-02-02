package adventofcode

import adventofcode2018.day9.Circle
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/day9")
class Day9Controller {

    var sequence = init()

    fun buildSequence(): Sequence<Circle> {
        return kotlin.sequences.sequence {
            val circle = Circle()
            var marble = 1
            do {
                yield(circle)
                circle.add(marble)
                marble++
            } while (true)
        }
    }

    fun init(): Iterator<Circle> {
        return buildSequence().iterator()
    }

    @Get("/reset")
    fun reset(){
        sequence = init()
    }

    @Get("/step")
    fun step(): Circle {
        return sequence.next()
    }
}