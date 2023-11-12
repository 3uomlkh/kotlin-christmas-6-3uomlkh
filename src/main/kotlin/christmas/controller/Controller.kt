package christmas.controller

import christmas.domain.Order
import christmas.domain.giftMenu
import christmas.domain.totalPrice
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
        outputView.printGiftMenu(giftMenu(total))
    }
}