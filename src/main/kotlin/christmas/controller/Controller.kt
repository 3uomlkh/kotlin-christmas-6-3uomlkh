package christmas.controller

import christmas.domain.Calculate.discountAmount
import christmas.domain.Calculate.totalAmountAfterDiscount
import christmas.domain.Calculate.totalAmountBeforeDiscount
import christmas.domain.Calculate.totalBenefitAmount
import christmas.domain.EventProcessing
import christmas.domain.EventResult
import christmas.domain.Order
import christmas.domain.checkGiftMenu
import christmas.domain.eventBadge
import christmas.utils.Constants.EVENT_APPLICABLE_AMOUNT
import christmas.utils.Constants.NO_BENEFIT
import christmas.utils.Constants.NO_EVENT
import christmas.utils.dateValidators
import christmas.utils.menuValidators
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    private var date : Int = 0
    private lateinit var menu : List<Order>
    private var total = 0
    private lateinit var result: EventResult
    private val eventProcessing = EventProcessing()

    fun start() {
        promotionInit() // 프로모션 시작 - 날짜와 메뉴를 받는다.
        eventPlanner()
    }

    private fun eventPlanner() {
        // 할인 전 총 주문 금액
        printBeforeDiscountAmount()
        // 증정메뉴
        printGiftMenu()
        // 혜택 내역
        getEventReult()

    }

    private fun promotionInit() {
        settingDate()
        settingMenu()
        printOrder()
    }

    private fun settingDate() {
        outputView.printAskDate()
        getDate()
    }

    private fun getDate() {
        while (true) {
            try {
                date = dateValidators(inputView.readDate())
                break
            } catch (error: IllegalArgumentException) {
                outputView.printInputDateError(error.message)
            }
        }
    }

    private fun settingMenu() {
        outputView.printAskMenu()
        getMenu()
    }

    private fun getMenu() {
        while (true) {
            try {
                menu = menuValidators(inputView.readDate())
                break
            } catch (error: IllegalArgumentException) {
                outputView.printInputMenuError(error.message)
            }
        }
    }

    private fun getEventReult() {
        if (isEventApplicable()) {
            result = eventProcessing.start(menu,date,total)
            val totalAmount = totalBenefitAmount(result)
            outputView.printBenefitDetails(result)
            outputView.printBenefitAmountTotal(totalAmount)
            outputView.printAfterDiscountTotal(totalAmountAfterDiscount(total,discountAmount(result)))
            outputView.printEventBadge(eventBadge(totalAmount))
        }
    }

    private fun isEventApplicable(): Boolean {
        if(total < EVENT_APPLICABLE_AMOUNT) {
            outputView.printNoBenefit()
            outputView.printBenefitAmountTotal(NO_BENEFIT)
            outputView.printAfterDiscountTotal(total)
            outputView.printEventBadge(NO_EVENT)
            return false
        }
        return true
    }

    private fun printOrder() {
        outputView.printStartMessage(date)
        outputView.printMenu(menu)
    }

    private fun printBeforeDiscountAmount() {
        total = totalAmountBeforeDiscount(menu)
        outputView.printPreDiscountTotal(total)
    }

    private fun printGiftMenu() {
        outputView.printGiftMenu(checkGiftMenu(total))
    }

    private fun printBenefits() {
        outputView.printBenefitDetails(result)
    }

    private fun printBenefitAmount() {
        val totalAmount = totalBenefitAmount(result)
        outputView.printBenefitAmountTotal(totalAmount)
    }
}





