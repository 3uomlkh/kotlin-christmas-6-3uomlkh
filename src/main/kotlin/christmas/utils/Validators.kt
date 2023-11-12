package christmas.utils

import christmas.domain.Menu
import christmas.utils.Constants.FIRST_DAY_OF_MONTH
import christmas.utils.Constants.LAST_DAY_OF_MONTH
import christmas.utils.Parser.inputParser
import christmas.utils.Parser.menuParser

enum class Validators(val message: String) {
    DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
}

fun dateValidators(date: String): Int {
    validateNumber(date)
    validateRange(date)
    return date.toInt()
}

fun menuValidators(order: String) {
    val parseOrder = inputParser(order)
    val result = menuParser(parseOrder)

    for (index in result.indices) {
        validateMenuSelection(result[index].menu)
    }
}

fun validateNumber(date: String) = require(date.toIntOrNull() != null)

fun validateRange(date: String) = require(date.toInt() in FIRST_DAY_OF_MONTH..LAST_DAY_OF_MONTH)

fun validateMenuSelection(menu: String) {
    val allDishes: List<String> = enumValues<Menu>().map { it.dish }
    require(allDishes.contains(menu))
}