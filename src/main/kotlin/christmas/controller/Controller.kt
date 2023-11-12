package christmas.controller

import christmas.utils.Parser.menuParser
import christmas.utils.Validators
import christmas.utils.dateValidators
import christmas.utils.menuValidators
import christmas.view.InputView
import christmas.view.OutputView
import java.awt.SystemColor.menu

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
        while (true) {
            try {
                menuValidators(inputView.readDate())
                break
            } catch (e: IllegalArgumentException) {
                outputView.printInputDateError()
            }
        }
    }
}