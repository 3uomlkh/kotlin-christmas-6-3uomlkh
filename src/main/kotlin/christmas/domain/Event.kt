package christmas.domain

import christmas.utils.Constants.WEEK


enum class Event(val discount: String, val price: Int) {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 100),
    WEEKDAYS("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SUNDAY("특별 할인", 1_000),
}

fun evenStart(menu: List<Order>, date: Int, total: Int) {
    if (christmasDdayCheck(date)) { // 26 ~ 31일
        afterChristmas(menu, date, total)
    }
    if(!christmasDdayCheck(date)) { // 1 ~ 25일
        inRangeChristmas(menu, date, total)
    }
}

fun inRangeChristmas(menu: List<Order>, date: Int, total: Int) {
    // 크리스마스 디데이 할인과 그 외 할인 적용
    val christmas = christmasDiscount(date)

    val original = EventResult(christmas = christmas)
    var new = EventResult()

    when (dayOfTheWeek(date)) {
        Event.WEEKDAYS.discount -> new = original.copy(weekday = weekDaysDiscount(menu))
        Event.WEEKEND.discount -> new = original.copy(weekend = weekEndDiscount(menu))
        Event.SUNDAY.discount -> println("특별 할인")
    }
}

fun afterChristmas(menu: List<Order>, date: Int, total: Int) {
    // 그 외 할인만 적용
    val original = EventResult()
    var new = EventResult()

    when (dayOfTheWeek(date)) {
        Event.WEEKDAYS.discount -> new = original.copy(weekday = weekDaysDiscount(menu))
        Event.WEEKEND.discount -> new = original.copy(weekend = weekEndDiscount(menu))
        Event.SUNDAY.discount -> println("특별 할인")
    }

    println(new.toString())
}

fun christmasDdayCheck(date: Int): Boolean {
    return date in 26..31
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

fun christmasDiscount(date: Int): Int {
    return 900 + Event.CHRISTMAS_D_DAY.price * date
}

fun weekDaysDiscount(order: List<Order>): Int {
    var discountPrice = 0
    for (index in order.indices) {
        val menu = order[index].menu
        val quantity = order[index].quantity.toInt()
        if(menu.equals(Menu.CHOCOLATE_CAKE.dish)
            || menu.equals(Menu.ICE_CREAM.dish)) {
            discountPrice += Event.WEEKDAYS.price * quantity
        }
    }
    return discountPrice
}

fun weekEndDiscount(order: List<Order>): Int {
    var discountPrice = 0
    for (index in order.indices) {
        val menu = order[index].menu
        val quantity = order[index].quantity.toInt()
        if(menu == Menu.T_BONE.dish
            || menu == Menu.BBQ.dish
            || menu == Menu.SEAFOOD_PASTA.dish
            || menu == Menu.CHRISTMAS_PASTA.dish
        ) {
            discountPrice += Event.WEEKDAYS.price * quantity
        }
    }
    return discountPrice
}