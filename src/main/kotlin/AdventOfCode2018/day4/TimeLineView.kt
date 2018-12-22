package AdventOfCode2018.day4

import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.time.LocalDateTime

class TimeLineView : View() {

    class RowModel(date:LocalDateTime, guardId:Int, action:ActionEnum){
        var guardId by property(guardId)
        fun guardIdProperty() = getProperty(RowModel::guardId)

        var date by property(date)
        fun dateProperty() = getProperty(RowModel::date)

        var action by property(action)
        fun actionProperty() = getProperty(RowModel::action)
    }

    override val root: BorderPane by fxml()
    val tableTimeLine: TableView<RowModel> by fxid()

    init {
        val items = readFile()
                .sortedBy(LogEntry::date)
                .map {
                    RowModel(it.date,it.guardId,it.action)
                }
                .observable()
        with(tableTimeLine) {
            this.items = items
            readonlyColumn("Date", RowModel::date)
            readonlyColumn("Guard ID", RowModel::guardId)
            readonlyColumn("Action", RowModel::action)
        }
    }
}
