package christmas.utils

import christmas.domain.Order
import christmas.utils.Constants.COMMA
import christmas.utils.Constants.HYPHEN

object Parser {
    fun inputParser(input: String): List<String> {
        return input.split(COMMA)
    }

    fun menuParser(menus: List<String>): List<Order> {
        val result = menus.map {
            val (menu, quantity) = it.split(HYPHEN)
            Order(menu, quantity)
        }
        return result
    }
}