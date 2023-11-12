package christmas.controller

import christmas.utils.Validators
import christmas.utils.dateValidators
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        outputView.printAskDate()
        while (true) {
            try {
                dateValidators(inputView.readDate())
                break
            } catch (e: IllegalArgumentException) {
                outputView.printInputDateError()
            }
        }

        outputView.printAskMenu()

    }
}