package christmas.domain

import christmas.utils.Constants.HUNDRED_TWENTY_THOUSAND
import java.awt.SystemColor.menu

enum class Menu(val type: String, val dish: String, val price: Int) {
    SOUP("애피타이저","양송이수프",6_000),
    TAPAS("애피타이저", "타파스", 5_500),
    SALAD("애피타이저", "시저샐러드", 8_000),
    T_BONE("메인", "티본스테이크", 55_000),
    BBQ("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타",35_000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15_000),
    ICE_CREAM("디저트", "아이스크림", 5_000),
    ZERO_COKE("음료", "제로콜라", 3_000),
    RED_WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000)
}

fun totalPrice(order: List<Order>): Int {
    var total = 0
    for (index in order.indices) {
        var menu = order[index].menu
        var quantity = order[index].quantity.toInt()
        when(menu) {
            Menu.SOUP.dish -> total += Menu.SOUP.price * quantity
            Menu.TAPAS.dish -> total += Menu.TAPAS.price * quantity
            Menu.SALAD.dish -> total += Menu.SALAD.price * quantity
            Menu.T_BONE.dish -> total += Menu.T_BONE.price * quantity
            Menu.BBQ.dish -> total += Menu.BBQ.price * quantity
            Menu.SEAFOOD_PASTA.dish -> total += Menu.SEAFOOD_PASTA.price * quantity
            Menu.CHRISTMAS_PASTA.dish -> total += Menu.CHRISTMAS_PASTA.price * quantity
            Menu.CHOCOLATE_CAKE.dish -> total += Menu.CHOCOLATE_CAKE.price * quantity
            Menu.ICE_CREAM.dish -> total += Menu.ICE_CREAM.price * quantity
            Menu.ZERO_COKE.dish -> total += Menu.ZERO_COKE.price * quantity
            Menu.RED_WINE.dish -> total += Menu.RED_WINE.price * quantity
            Menu.CHAMPAGNE.dish -> total += Menu.CHAMPAGNE.price * quantity
        }
    }
    return total
}