package christmas

import christmas.controller.Controller
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val controller = Controller(InputView(), OutputView())
    controller.start()
}
