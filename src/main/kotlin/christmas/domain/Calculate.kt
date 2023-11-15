package christmas.domain

object Calculate {
    fun totalAmountBeforeDiscount(order: List<Order>): Int {
        var total = 0
        for (index in order.indices) {
            val menu = order[index].menu
            val quantity = order[index].quantity.toInt()
            total += getMenuPrice(menu, quantity)
        }
        return total
    }

    private fun getMenuPrice(order: String, quantity: Int): Int {
        var total = 0
        for (menu in Menu.values()) {
            when(order) {
                menu.dish -> total += menu.price * quantity
            }
        }
        return total
    }

    fun totalBenefitAmount(result: EventResult): Int = result.events.sum()

    fun discountAmount(result: EventResult): Int = result.events.dropLast(1).sum()

    fun totalAmountAfterDiscount(total: Int, discount: Int): Int = total-discount
}