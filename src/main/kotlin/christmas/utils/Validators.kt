package christmas.utils

import christmas.domain.Menu
import christmas.domain.Order
import christmas.utils.Constants.COMMA
import christmas.utils.Parser.inputParser
import christmas.utils.Parser.menuAndQuantityParser
import christmas.utils.Parser.menuParser

enum class Validators(val message: String, val first: Int, val last: Int) {
    DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.", 1, 31),
    MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", 1, 20),
    TOTAL("[ERROR] 메뉴는 20개 이상 주문할 수 없습니다.", 1, 20),
    DRINK("[ERROR] 음료만 주문할 수 없습니다.",0,0)
}

fun dateValidators(date: String): Int {
    validateNumber(date)
    validateRange(date)
    return date.toInt()
}

fun menuValidators(order: String): List<Order> {
    var total = 0
    val parseOrder = validateMenuFormComma(order)
    validateMenuDuplicate(parseOrder)
    val result = validateMenuFormHyphen(parseOrder)
    for (index in result.indices) {
        val menu = result[index].menu
        val quantity = result[index].quantity
        validateMenuSelection(menu)
        validateMenuNumber(quantity)
        validateMenuQuantityRange(quantity)
        total += quantity.toInt()
    }
    validateOrderDrink(parseOrder)
    validateOrderTotal(total)
    return result
}

fun validateNumber(date: String) = require(date.toIntOrNull() is Int) {Validators.DATE.message}

fun validateRange(date: String) = require(date.toInt() in Validators.DATE.first..Validators.DATE.last) {Validators.DATE.message}

fun validateMenuDuplicate(order: List<String>) {
    val result = menuParser(order)
    require(result.size == result.distinct().size) {Validators.MENU.message}
}

fun validateMenuFormComma(order: String): List<String> {
    if(inputParser(order).size > 1) {
        require(order.contains(COMMA)) {Validators.MENU.message}
    }
    return inputParser(order)
}

fun validateMenuFormHyphen(order: List<String>): List<Order> {
    for (menu in order) {
        require(isValidMenuFormatHyphen(menu)) {Validators.MENU.message}
    }
    return menuAndQuantityParser(order)
}

fun validateMenuSelection(menu: String) {
    val allDishes: List<String> = enumValues<Menu>().map { it.dish }
    require(allDishes.contains(menu)) {Validators.MENU.message}
}

fun validateMenuNumber(quantity: String) = require(quantity.toIntOrNull() is Int) {Validators.DATE.message}

fun validateMenuQuantityRange(quantity: String) = require(quantity.toInt() in Validators.MENU.first..Validators.MENU.last) {Validators.MENU.message}

fun validateOrderDrink(order: List<String>) {
    val result = menuParser(order)
    require(result.contains(Menu.SOUP.dish)
            || result.contains(Menu.TAPAS.dish)
            || result.contains(Menu.SALAD.dish)
            || result.contains(Menu.T_BONE.dish)
            || result.contains(Menu.BBQ.dish)
            || result.contains(Menu.SEAFOOD_PASTA.dish)
            || result.contains(Menu.CHRISTMAS_PASTA.dish)
            || result.contains(Menu.CHOCOLATE_CAKE.dish)
            || result.contains(Menu.ICE_CREAM.dish)) {Validators.DRINK.message}
}

fun validateOrderTotal(quantity: Int) = require(quantity in Validators.TOTAL.first..Validators.TOTAL.last) { Validators.TOTAL.message }

fun isValidMenuFormatHyphen(input: String): Boolean {
    val regex = Regex("^[^,]+-\\d+$")
    return regex.matches(input)
}