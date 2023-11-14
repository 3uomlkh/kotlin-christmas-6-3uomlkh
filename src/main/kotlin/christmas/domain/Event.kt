package christmas.domain

import christmas.utils.Constants

enum class Event(
    val discount: String,
    val price: Int
) {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 100),
    WEEKDAYS("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SUNDAY("특별 할인", 1_000),
    GIFT("증정 이벤트", 25_000),
}

fun allEvent(result: EventResult): Int = result.events.sum()

fun discountAmount(result: EventResult): Int = result.events.dropLast(1).sum()

fun totalPrice(total: Int, discount: Int): Int = total-discount

fun checkGiftMenu(total: Int): Boolean = total >= Constants.HUNDRED_TWENTY_THOUSAND