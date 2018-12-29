package adventofcode

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("adventofcode")
                .mainClass(Application.javaClass)
                .start()

    }
}