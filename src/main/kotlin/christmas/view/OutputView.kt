package christmas.view

import christmas.domain.Menu
import christmas.domain.Order
import christmas.utils.Constants.AFTER_DISCOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.BENEFIT_AMOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.BENEFIT_DETAILS_MESSAGE
import christmas.utils.Constants.DATE_MESSAGE
import christmas.utils.Constants.DECEMBER_EVENT_BADGE
import christmas.utils.Constants.GIFT_MENU_MESSAGE
import christmas.utils.Constants.NO_EVENT
import christmas.utils.Constants.ORDER_MENU_MESSAGE
import christmas.utils.Constants.ORDER_MESSAGE
import christmas.utils.Constants.PRE_DISCOUNT_TOTAL_MESSAGE
import christmas.utils.Constants.START_MESSAGE
import java.text.NumberFormat
import java.util.Locale

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

    fun printMenu(menu: List<Order>) {
        println(ORDER_MENU_MESSAGE)
        for(index in menu.indices) {
            println("${menu[index].menu} ${menu[index].quantity}개")
        }
        println()
    }

    fun printPreDiscountTotal(total: Int) {
        println(PRE_DISCOUNT_TOTAL_MESSAGE)
        println(numberFormatting(total))
        println()
    }

    fun printGiftMenu(gift: Boolean) {
        println(GIFT_MENU_MESSAGE)
        if(gift) {
            println("${Menu.CHAMPAGNE.dish} 1개")
            return
        }
        println(NO_EVENT)
        println()
    }

    fun printBenefitDetails(input: String) {
        println(BENEFIT_DETAILS_MESSAGE)
        println(input)
        println()
    }

    fun printBenefitAmountTotal(input: String) {
        println(BENEFIT_AMOUNT_TOTAL_MESSAGE)
        println(input)
        println()
    }

    fun printAfterDiscountTotal(total: Int) {
        println(AFTER_DISCOUNT_TOTAL_MESSAGE)
        println(numberFormatting(total))
        println()
    }

    fun printEventBadge(input: String) {
        println(DECEMBER_EVENT_BADGE)
        println(input)
        println()
    }

    fun printInputDateError(error: String?) {
        println(error)
    }

    fun printInputMenuError(error: String?) {
        println(error)
    }

    private fun numberFormatting(input: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        return numberFormat.format(input)
    }
}