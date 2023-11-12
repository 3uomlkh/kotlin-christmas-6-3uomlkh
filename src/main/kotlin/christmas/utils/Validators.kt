package christmas.utils

import christmas.utils.Constants.FIRST_DAY_OF_MONTH
import christmas.utils.Constants.LAST_DAY_OF_MONTH
import christmas.utils.Parser.inputParser
import christmas.utils.Parser.menuParser
import java.awt.SystemColor.menu

enum class Validators(val message: String) {
    DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
}

fun dateValidators(date: String) {
    validateNumber(date)
    validateRange(date)
}

fun menuValidators(order: String) {
    val parseOrder = inputParser(order)
    val result = menuParser(parseOrder)

    for (index in result.indices) {
        println(result[index].menu)
        println(result[index].quantity)
    }
}

fun validateNumber(date: String) = require(date.toIntOrNull() != null)

fun validateRange(date: String) = require(date.toInt() in FIRST_DAY_OF_MONTH..LAST_DAY_OF_MONTH)

