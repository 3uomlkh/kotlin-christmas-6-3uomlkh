package christmas.domain

enum class Badge(val type: String, val price: Int) {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000)
}

fun eventBadge(discount: Int): String {
    return when {
        discount >= Badge.STAR.price -> Badge.STAR.type
        discount >= Badge.TREE.price -> Badge.TREE.type
        discount >= Badge.SANTA.price -> Badge.SANTA.type
        else -> ""
    }
}