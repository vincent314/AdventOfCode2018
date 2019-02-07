package adventofcode

import adventofcode2018.day7.getSequence
import adventofcode2018.day7.readFile
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.io.File

@Controller("/day7")
class Day7Controller {

    class VertexDTO(val value: Char, val next: List<Char>, val previous: List<Char>)

    @Get("/vertices")
    fun vertices(): List<VertexDTO> {
        val vertices = readFile(File("input", "day7.txt"))
        return vertices.values
                .map { vertex ->
                    VertexDTO(
                            vertex.step,
                            vertex.next.map { it.step },
                            vertex.previous.map { it.step }
                    )
                }
    }
}