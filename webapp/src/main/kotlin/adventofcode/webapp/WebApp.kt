package adventofcode.webapp

import kotlin.browser.window
import kotlin.js.Promise

@JsModule("moment")
external fun moment(): Moment
@JsModule("moment")
external fun moment(values: Array<Int>): Moment

external interface Moment {
    fun format(format: String): String
}

@JsName("day4")
fun day4() {
    println(moment().format("YYYY-MM-DD"))
    window.fetch("http://localhost:8080/day4/entries")
            .then { it.json().asDynamic() as Promise<Array<LogEntry>> }
            .then { entries ->
//                console.log(entries.map { moment(it.date).format("YYYY-MM-DD") })
//                console.log(entries)
                entries.forEach {
                    println(moment(it.date).format("YYYY-MM-DD HH:mm"))
                }
            }
}

fun main(){
    day4()
}