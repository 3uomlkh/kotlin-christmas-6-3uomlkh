package christmas.domain

import christmas.utils.Constants
import christmas.utils.Constants.CHRISTMAS
import christmas.utils.Constants.D_DAY_DISCOUNT_MIN
import christmas.utils.Constants.GIFT_APPLICABLE_AMOUNT
import christmas.utils.Constants.NO_EVENT
import christmas.utils.Constants.SPECIAL_DISCOUNT_AMOUNT

class EventProcessing {

    private var events: MutableList<Int> = mutableListOf(0,0,0,0,0)

    fun start(menu: List<Order>, date: Int, total: Int): EventResult {
        if (christmasDdayCheck(date)) {
            return afterChristmas(menu, date, total)
        }
        return inRangeChristmas(menu, date, total)
    }

    fun inRangeChristmas(menu: List<Order>, date: Int, total: Int): EventResult {
        christmasEvent(menu, date)

        events[XMAS] = christmasDiscount(date)
        events[GIFT] = giftMenuEvent(total)
        when (dayOfTheWeek(date)) {
            Event.WEEKDAYS.discount -> events[WEEKDAY] = weekDayDiscount(menu)
            Event.WEEKEND.discount -> events[WEEKEND] = weekEndDiscount(menu)
            Event.SUNDAY.discount -> {
                events[WEEKDAY] = weekDayDiscount(menu)
                events[SPECIAL] = SPECIAL_DISCOUNT_AMOUNT
            }
        }
        return EventResult(events)
    }

    fun christmasEvent(menu: List<Order>, date: Int) {
        if (date == CHRISTMAS) {
            events[WEEKDAY] = weekDayDiscount(menu)
            events[SPECIAL] = SPECIAL_DISCOUNT_AMOUNT
        }
    }

    fun afterChristmas(menu: List<Order>, date: Int, total: Int): EventResult {
        val gift = giftMenuEvent(total)
        events[GIFT] = gift
        when (dayOfTheWeek(date)) {
            Event.WEEKDAYS.discount -> events[WEEKDAY] = weekDayDiscount(menu)
            Event.WEEKEND.discount -> events[WEEKEND] = weekEndDiscount(menu)
            Event.SUNDAY.discount -> {
                events[WEEKDAY] = weekDayDiscount(menu)
                events[SPECIAL] = SPECIAL_DISCOUNT_AMOUNT
            }
        }
        return EventResult(events)
    }

    private fun christmasDdayCheck(date: Int): Boolean {
        return date in 26..31
    }

    fun dayOfTheWeek(date: Int): String {
        return when (date % Constants.WEEK) {
            in 4..6 -> Event.WEEKDAYS.discount
            0 -> Event.WEEKDAYS.discount
            1, 2 -> Event.WEEKEND.discount
            3 -> Event.SUNDAY.discount
            else -> NO_EVENT
        }
    }

    fun christmasDiscount(date: Int): Int {
        return D_DAY_DISCOUNT_MIN + Event.CHRISTMAS_D_DAY.price * date
    }

    fun weekDayDiscount(order: List<Order>): Int {
        var discountPrice = 0
        for (index in order.indices) {
            val menu = order[index].menu
            val quantity = order[index].quantity.toInt()
            if(menu == Menu.CHOCOLATE_CAKE.dish
                || menu == Menu.ICE_CREAM.dish) discountPrice += Event.WEEKDAYS.price * quantity
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
            ) discountPrice += Event.WEEKDAYS.price * quantity
        }
        return discountPrice
    }

    private fun giftMenuEvent(total: Int): Int {
        if (total >= GIFT_APPLICABLE_AMOUNT) return Event.GIFT.price
        return 0
    }

    companion object {
        const val XMAS = 0
        const val WEEKDAY = 1
        const val WEEKEND = 2
        const val SPECIAL = 3
        const val GIFT = 4
    }
}