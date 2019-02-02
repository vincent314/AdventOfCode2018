package adventofcode2018

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File

class Solutions {
    class Day<T, U> {
        var part1: T? = null
        var part2: U? = null
    }

    var day1 = Day<Long, Long>()
    var day2 = Day<Int, String>()
    var day8 = Day<Int, Int>()
    var day9 = Day<Long, Long>()
    var day11 = Day<String,String>()

    companion object {
        fun readFile():Solutions{
            val mapper = ObjectMapper(YAMLFactory())
            return mapper.readValue<Solutions>(File("../","solutions.yml"), Solutions::class.java)
        }
    }
}