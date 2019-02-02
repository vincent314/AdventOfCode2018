package adventofcode2018.day4

import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.lang.String.format
import java.time.LocalDateTime

class TimeLineView : View() {

    class RowModel(date: LocalDateTime, guardId: Int, action: ActionEnum) {
        var guardId by property(guardId)
        fun guardIdProperty() = getProperty(RowModel::guardId)

        var date by property(date)
        fun dateProperty() = getProperty(RowModel::date)

        var action by property(action)
        fun actionProperty() = getProperty(RowModel::action)
    }

    class CounterRowModel(guardId: Int, counters: String, max: Int) {
        var guardId by property(guardId)
        fun guardIdProperty() = getProperty(CounterRowModel::guardId)

        var counters by property(counters)
        fun countersProperty() = getProperty(CounterRowModel::counters)

        var max by property(max)
        fun maxProperty() = getProperty(CounterRowModel::max)
    }

    override val root: BorderPane by fxml()
    val tableTimeLine: TableView<RowModel> by fxid()
    val countersTable: TableView<CounterRowModel> by fxid()

    init {
        val items = readFile()
                .sortedBy(LogEntry::date)
                .map {
                    RowModel(it.date, it.guardId, it.action)
                }
                .observable()
        with(tableTimeLine) {
            this.items = items
            readonlyColumn("Date", RowModel::date)
            readonlyColumn("Guard ID", RowModel::guardId)
            readonlyColumn("Action", RowModel::action)
        }


        val countersModel = readFile()
                .groupBy(LogEntry::guardId)
                .mapValues { (_, entries) -> entries.getGuardSleepRanges() }
                .mapValues { (_, ranges) -> ranges.countSleep() }
                .map { (guardId, counters) -> CounterRowModel(
                        guardId,
                        counters.joinToString(" ") { format("%2d", it) },
                        0
                        ) }
                .observable()

        with(countersTable) {
            this.items = countersModel
            readonlyColumn("Guard ID", CounterRowModel::guardId)
            readonlyColumn("counters", CounterRowModel::counters)
            readonlyColumn("max", CounterRowModel::max)
        }

    }
}
