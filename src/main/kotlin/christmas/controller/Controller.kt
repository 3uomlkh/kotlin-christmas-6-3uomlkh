package christmas.controller

import christmas.domain.Order
import christmas.domain.allEvent
import christmas.domain.checkGiftMenu
import christmas.domain.discountAmount
import christmas.domain.evenStart
import christmas.domain.eventBadge
import christmas.domain.totalPrice
import christmas.utils.Constants.EVENT_APPLICABLE_AMOUNT
import christmas.utils.Constants.NO_EVENT
import christmas.utils.Constants.NO_EVENT_AMOUNT
import christmas.utils.dateValidators
import christmas.utils.menuValidators
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        var date : Int
        var day : String
        var menu : List<Order>

        outputView.printAskDate()
        while (true) {
            try {
                date = dateValidators(inputView.readDate())
                break
            } catch (error: IllegalArgumentException) {
                outputView.printInputDateError(error.message)
            }
        }

        outputView.printAskMenu()
        while (true) {
            try {
                menu = menuValidators(inputView.readDate())
                break
            } catch (error: IllegalArgumentException) {
                outputView.printInputMenuError(error.message)
            }
        }

        outputView.printStartMessage(date)
        outputView.printMenu(menu)
        val total = totalPrice(menu)
        outputView.printPreDiscountTotal(total)
        outputView.printGiftMenu(checkGiftMenu(total))

        if (isEventApplicable(total)) {
           val eventResult = evenStart(menu,date,total)
            println("총혜택 금액 -" + allEvent(eventResult))
            println("이벤트 배지 -" + eventBadge(allEvent(eventResult)))
            println("할인 후 예상 결제 금액 -" + totalPrice(total,discountAmount(eventResult)))
        }
    }
}

fun isEventApplicable(total: Int): Boolean {
    val outputView = OutputView()
    if(total < EVENT_APPLICABLE_AMOUNT) {
        outputView.printBenefitDetails(NO_EVENT)
        outputView.printBenefitAmountTotal(NO_EVENT_AMOUNT)
        outputView.printAfterDiscountTotal(total)
        outputView.printEventBadge(NO_EVENT)
        return false
    }
    return true
}



