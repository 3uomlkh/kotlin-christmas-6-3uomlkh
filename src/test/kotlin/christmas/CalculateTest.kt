package christmas

import christmas.utils.Calculate.totalAmountAfterDiscount
import christmas.utils.Calculate.totalAmountBeforeDiscount
import christmas.utils.Calculate.totalBenefitAmount
import christmas.data.EventResult
import christmas.data.Order
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CalculateTest {
    @Test
    @DisplayName("할인 전 총주문 금액 계산 테스트")
    fun BeforeDiscountAmountTest() {
        // given
        val input = listOf(Order("바비큐립","1"), Order("초코케이크","2"))

        // when
        val result = totalAmountBeforeDiscount(input)

        // then
        Assertions.assertThat(result).isEqualTo(84000)
    }

    @Test
    @DisplayName("총혜택 금액 계산 기능 테스트")
    fun allBenefitAmountTest() {
        // given
        val input = EventResult(mutableListOf(1200,4046,0,1000,0))

        // when
        val result = totalBenefitAmount(input)

        // then
        Assertions.assertThat(result).isEqualTo(6246)
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산 기능 테스트")
    fun AfterDiscountAmountTest() {
        // given
        val total = 76000
        val discount = 4123

        // when
        val result = totalAmountAfterDiscount(total,discount)

        // then
        Assertions.assertThat(result).isEqualTo(71877)
    }
}