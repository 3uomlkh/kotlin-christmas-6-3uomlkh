package christmas.utils

import christmas.domain.Menu
import christmas.utils.Constants.COMMA
import christmas.utils.Constants.HYPHEN
import christmas.utils.Parser.dulicatedMenuParser
import christmas.utils.Parser.inputParser
import christmas.utils.Parser.menuParser
import java.awt.SystemColor.menu

enum class Validators(val message: String, val first: Int, val last: Int) {
    DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.", 1, 31),
    MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", 1, 20)
}

fun dateValidators(date: String): Int {
    validateNumber(date)
    validateRange(date)
    return date.toInt()
}

fun menuValidators(order: String) {

    val parseOrder = validateMenuFormComma(order)
    validateMenuDulicate(parseOrder)
    validateMenuFormHypen(parseOrder)

    val result = menuParser(parseOrder)
    for (index in result.indices) {
        var menus = result[index].menu
        var quantities = result[index].quantity
        validateMenuSelection(menus)
        validateMenuNumber(quantities)
        validateMenuQuantityRange(quantities)
    }
}

fun validateNumber(date: String) = require(date.toIntOrNull() is Int)

fun validateRange(date: String) = require(date.toInt() in Validators.DATE.first..Validators.DATE.last)

fun validateMenuSelection(menu: String) {
    val allDishes: List<String> = enumValues<Menu>().map { it.dish }
    require(allDishes.contains(menu))
}

fun validateMenuNumber(quantity: String) = require(quantity.toIntOrNull() is Int)

fun validateMenuQuantityRange(quantity: String) = require(quantity.toInt() in Validators.MENU.first..Validators.MENU.last)

fun validateMenuDulicate(order: List<String>) {
    val result = dulicatedMenuParser(order)
    require(result.size == result.distinct().size)
}

fun validateMenuFormComma(order: String): List<String> {
    require(isValidMenuFormatComma(order))
    return inputParser(order)
}

fun validateMenuFormHypen(order: List<String>) {
    for (menu in order) {
        require(isValidMenuFormatHypen(menu))
    }
}

fun isValidMenuFormatHypen(input: String): Boolean {
    val regex = Regex("^[^,]+-\\d+$")
    return regex.matches(input)
}

fun isValidMenuFormatComma(input: String): Boolean {
    val regex = Regex("^[^,]+(,[^,]+)*$")
    return regex.matches(input)
}