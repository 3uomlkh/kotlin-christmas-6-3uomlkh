package christmas.domain

import christmas.utils.Constants.WEEK


enum class Event(val discount: String, val price: Int) {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 100),
    WEEKDAYS("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SUNDAY("특별 할인", 1_000),
}

fun evenStart(menu: List<Order>, date: Int, total: Int) {

    when (dayOfTheWeek(date)) {
        Event.WEEKDAYS.discount -> println("평일")
        Event.WEEKEND.discount -> println("주말")
        Event.SUNDAY.discount -> println("일요일")
    }

}

fun dayOfTheWeek(date: Int): String {
    return when (date % WEEK) {
        in 4..6 -> Event.WEEKDAYS.discount
        0 -> Event.WEEKDAYS.discount
        1, 2 -> Event.WEEKEND.discount
        3 -> Event.SUNDAY.discount
        else -> "NONE"
    }
}
