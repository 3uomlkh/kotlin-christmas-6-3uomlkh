package christmas

import christmas.domain.EventProcessing
import christmas.data.Order
import christmas.domain.checkGiftMenu
import christmas.domain.eventBadge
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EventTest {
    @Test
    @DisplayName("총주문 금액이 12만원 이상일 경우 증정 이벤트 적용 테스트")
    fun giftMenuTest() {
        // given
        val total = 120000

        // when
        val result = checkGiftMenu(total)

        // then
        Assertions.assertThat(result).isEqualTo(true)
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 기능 테스트")
    fun christmasDdayDiscountTest() {
        // given
        val date = 12

        // when
        val result = EventProcessing().christmasDiscount(date)

        // then
        Assertions.assertThat(result).isEqualTo(2100)
    }

    @Test
    @DisplayName("평일 할인 기능 테스트")
    fun weekdayDiscountTest() {
        // given
        val menu = listOf(Order("바비큐립","1"), Order("초코케이크","2"))

        // when
        val result = EventProcessing().weekDayDiscount(menu)

        // then
        Assertions.assertThat(result).isEqualTo(4046)
    }

    @Test
    @DisplayName("주말 할인 기능 테스트")
    fun weekendDiscountTest() {
        // given
        val menu = listOf(Order("바비큐립","1"), Order("초코케이크","2"))

        // when
        val result = EventProcessing().weekEndDiscount(menu)

        // then
        Assertions.assertThat(result).isEqualTo(2023)
    }

    @Test
    @DisplayName("배지 부여 기능 테스트")
    fun badgeEventTest() {
        // given
        val discountAmount = 29046

        // when
        val result = eventBadge(discountAmount)

        // then
        Assertions.assertThat(result).isEqualTo("산타")
    }
}