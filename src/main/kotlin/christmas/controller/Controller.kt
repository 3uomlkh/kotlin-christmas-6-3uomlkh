package christmas.controller

import christmas.domain.Event
import christmas.domain.EventProcessing
import christmas.domain.EventResult
import christmas.domain.Order
import christmas.domain.checkGiftMenu
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
    private var date : Int = 0
    private lateinit var menu : List<Order>
    private var total = 0
    private lateinit var eventResult: EventResult

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
            val eventProcessing = EventProcessing()
            eventResult = eventProcessing.evenStart(menu,date,total)



//            println(eventResult)
//            println("총혜택 금액 : " + allEvent(eventResult))
//            println("이벤트 배지 : " + eventBadge(allEvent(eventResult)))
//            println("할인 후 예상 결제 금액 : " + totalPrice(total,discountAmount(eventResult)))
        }
    }

    private fun isEventApplicable(): Boolean {
        if(total < EVENT_APPLICABLE_AMOUNT) {
            outputView.printBenefitDetails(NO_EVENT)
            outputView.printBenefitAmountTotal(NO_EVENT_AMOUNT)
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
        total = totalPrice(menu)
        outputView.printPreDiscountTotal(total)

    }

    private fun printGiftMenu() {
        outputView.printGiftMenu(checkGiftMenu(total))
    }

    private fun printWeekdayDiscount() {
//        if(eventResult.christmas == 0) {
//
//        }
//
//        println("${Event.CHRISTMAS_D_DAY.discount}:-${eventResult.christmas}")
//        println("${Event.WEEKDAYS.discount}:")
//        println("${Event.WEEKEND.discount}:")
//        println("${Event.SUNDAY.discount}:")
//        println("${Event.GIFT.discount}:")
    }

    private fun printBenefits() {
//        outputView.printBenefitDetails()
    }
}





