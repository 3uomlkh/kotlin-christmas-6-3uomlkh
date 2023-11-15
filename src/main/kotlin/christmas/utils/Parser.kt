package christmas.utils

import christmas.domain.Order
import christmas.utils.Constants.COMMA
import christmas.utils.Constants.HYPHEN

object Parser {
    fun inputParser(input: String): List<String> {
        val result = input.replace(" ","")
        return result.split(COMMA)
    }

    fun menuAndQuantityParser(menus: List<String>): List<Order> {
        val result = menus.map {
            val (menu, quantity) = it.split(HYPHEN)
            Order(menu, quantity)
        }
        return result
    }

    fun menuParser(menus: List<String>): List<String> {
        val result = menus.flatMap {
            val (menu) = it.split(HYPHEN)
            listOf(menu)
        }
        return result
    }
}