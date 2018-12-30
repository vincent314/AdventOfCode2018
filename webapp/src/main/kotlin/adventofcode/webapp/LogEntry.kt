package adventofcode.webapp

enum class ActionEnum {
    BEGINS_SHIFT, FALLS_ASLEEP, WAKES_UP
}

class LogEntry(var action: ActionEnum, var date: Array<Int>, var guardId: Int = 0)