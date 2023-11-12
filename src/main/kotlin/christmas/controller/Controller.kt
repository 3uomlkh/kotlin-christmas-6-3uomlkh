package christmas.controller

import christmas.utils.dateValidators
import christmas.utils.menuValidators
import christmas.utils.validateMenuDulicate
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        var date : Int

        outputView.printAskDate()
        while (true) {
            try {
                date = dateValidators(inputView.readDate())
                break
            } catch (e: IllegalArgumentException) {
                outputView.printInputDateError()
            }
        }

        outputView.printAskMenu()
        while (true) {
            try {
                menuValidators(inputView.readDate())
                break
            } catch (e: IllegalArgumentException) {
                outputView.printInputMenuError()
            }
        }

        outputView.printStartMessage(date)
    }
}