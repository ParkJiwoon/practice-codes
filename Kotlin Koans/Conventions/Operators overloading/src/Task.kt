import TimeInterval.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

// Supported intervals that might be added to dates:
enum class TimeInterval { DAY, WEEK, YEAR }

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun TimeInterval.times(number: Int) =
    RepeatedTimeInterval(this, number)

operator fun MyDate.plus(
    timeInterval: TimeInterval
): MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(
    timeIntervals: RepeatedTimeInterval
): MyDate = this.addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)


fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}
