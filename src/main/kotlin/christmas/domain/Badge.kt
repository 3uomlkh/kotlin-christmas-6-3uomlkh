package christmas.domain

import christmas.utils.Constants.MAX_ORDER_PRICE

enum class Badge(val type: String, val price: Int) {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000)
}

fun eventBadge(discount: Int): String {
    return when(discount) {
        in Badge.STAR.price until Badge.TREE.price -> Badge.STAR.type
        in Badge.TREE.price until Badge.SANTA.price -> Badge.TREE.type
        in Badge.SANTA.price..MAX_ORDER_PRICE -> Badge.SANTA.type
        else -> ""
    }
}