package christmas.view

import christmas.utils.Constants.AFTER_DISCOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.BENEFIT_AMOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.BENEFIT_DETAILS_MESSAGE
import christmas.utils.Constants.DATE_MESSAGE
import christmas.utils.Constants.DECEMBER_EVENT_BADGE
import christmas.utils.Constants.GIFT_MENU_MESSAGE
import christmas.utils.Constants.ORDER_MENU_MESSAGE
import christmas.utils.Constants.ORDER_MESSAGE
import christmas.utils.Constants.PRE_DISCOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.START_MESSAGE
import christmas.utils.Validators

class OutputView {
    fun printAskDate() {
        println(DATE_MESSAGE)
    }

    fun printAskMenu() {
        println(ORDER_MESSAGE)
    }

    fun printStartMessage(input: Int) {
        println(String.format(START_MESSAGE, input))
    }

    fun printMenu() {
        println(ORDER_MENU_MESSAGE)
    }

    fun printPreDiscountTotal() {
        println(PRE_DISCOUNT_TOTAL_MESSAGE)
    }

    fun printGiftMenu() {
        println(GIFT_MENU_MESSAGE)
    }

    fun printBenefitDetails() {
        println(BENEFIT_DETAILS_MESSAGE)
    }

    fun printBenefitAmountTotal() {
        println(BENEFIT_AMOUNT_TOTAL_MESSAGE)
    }

    fun printAfterDiscountTotal() {
        println(AFTER_DISCOUNT_TOTAL_MESSAGE)
    }

    fun printEventBadge() {
        println(DECEMBER_EVENT_BADGE)
    }

    fun printInputDateError(error: String?) {
        println(error)
    }

    fun printInputMenuError(error: String?) {
        println(error)
    }
}