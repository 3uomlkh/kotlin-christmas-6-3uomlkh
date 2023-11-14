package christmas

import christmas.utils.validateNumber
import christmas.utils.validateRange
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
}