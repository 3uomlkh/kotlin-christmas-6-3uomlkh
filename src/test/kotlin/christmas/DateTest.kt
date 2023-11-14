package christmas

import christmas.domain.EventProcessing
import christmas.utils.validateNumber
import christmas.utils.validateRange
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateTest {
    @ValueSource(strings = ["3일", "three", "@&#%$3"])
    @ParameterizedTest
    @DisplayName("날짜가 정수가 아닐 경우 예외가 발생한다.")
    fun dateInvalidFormatTest(input: String) {
        assertThrows<IllegalArgumentException> {
            validateNumber(input)
        }
    }

    @ValueSource(strings = ["0", "32", "999"])
    @ParameterizedTest
    @DisplayName("날짜가 1 ~ 31 사이의 수가 아닐 경우 예외가 발생한다.")
    fun dateInvalidRangeTest(input: String) {
        assertThrows<IllegalArgumentException> {
            validateRange(input)
        }
    }

    @Test
    @DisplayName("날짜에 따라 평일, 주말, 특별 할인으로 분류되는지 테스트")
    fun classifyDatesTest() {
        // given
        val eventProcessing = EventProcessing()

        // when & then
        assertThat(eventProcessing.dayOfTheWeek(4)).isEqualTo("평일 할인")
        assertThat(eventProcessing.dayOfTheWeek(9)).isEqualTo("주말 할인")
        assertThat(eventProcessing.dayOfTheWeek(10)).isEqualTo("특별 할인")
    }
}