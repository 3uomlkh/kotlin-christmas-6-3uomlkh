package christmas.controller

import christmas.utils.Calculate.discountAmount
import christmas.utils.Calculate.totalAmountAfterDiscount
import christmas.utils.Calculate.totalAmountBeforeDiscount
import christmas.utils.Calculate.totalBenefitAmount
import christmas.domain.EventProcessing
import christmas.data.EventResult
import christmas.data.Order
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
    private var total = 0
    private var totalAmount = 0
    private lateinit var menu : List<Order>
    private lateinit var result: EventResult
    private val eventProcessing = EventProcessing()

    fun start() {
        promotionInit()
        eventPlanner()
    }

    private fun eventPlanner() {
        printBeforeDiscountAmount()
        printGiftMenu()
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
            printBenefits()
            printBenefitAmount()
            printAfterDiscount()
            printBadge()
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

    private fun printGiftMenu() = outputView.printGiftMenu(checkGiftMenu(total))

    private fun printBenefits() = outputView.printBenefitDetails(result)

    private fun printBenefitAmount() {
        totalAmount = totalBenefitAmount(result)
        outputView.printBenefitAmountTotal(totalAmount)
    }

    private fun printAfterDiscount() = outputView.printAfterDiscountTotal(totalAmountAfterDiscount(total,discountAmount(result)))

    private fun printBadge() = outputView.printEventBadge(eventBadge(totalAmount))
}





