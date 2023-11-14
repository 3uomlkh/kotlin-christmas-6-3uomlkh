package christmas

import christmas.domain.EventProcessing
import christmas.domain.Order
import christmas.utils.Parser.inputParser
import christmas.utils.Parser.menuAndQuantityParser
import christmas.utils.validateMenuDuplicate
import christmas.utils.validateMenuFormComma
import christmas.utils.validateMenuFormHyphen
import christmas.utils.validateMenuNumber
import christmas.utils.validateMenuQuantityRange
import christmas.utils.validateMenuSelection
import christmas.utils.validateOrderDrink
import christmas.utils.validateOrderTotal
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MenuTest {
    @Test
    @DisplayName("메뉴가 콤마(,)로 나눠져 있지 않을 시 예외가 발생한다.")
    fun menuFormatCommaTest() {
        // given
        val input = "티본스테이크-1/바비큐립-1*초코케이크-1"

        // when & then
        assertThrows<IllegalArgumentException> {
            validateMenuFormComma(input)
        }
    }

    @Test
    @DisplayName("중복된 메뉴가 있을 시 예외가 발생한다.")
    fun menuDuplicateTest() {
        // given
        val input = listOf("티본스테이크-1","초코케이크-2","티본스테이크-2")

        // when & then
        assertThrows<IllegalArgumentException> {
            validateMenuDuplicate(input)
        }
    }

    @ValueSource(strings = ["티본스테이크:1","초코케이크/2","아이스크림2"])
    @ParameterizedTest
    @DisplayName("메뉴와 개수가 하이픈(-)으로 나눠져 있지 않은 경우 예외가 발생한다.")
    fun menuFormatHyphenTest(input: String) {
        // given
        val inputs = mutableListOf("레드와인-1")
        inputs.add(input)

        // when & then
        assertThrows<IllegalArgumentException> {
            validateMenuFormHyphen(inputs)
        }
    }

    @ValueSource(strings = ["초코아이스크림","로제파스타","딸기케이크"])
    @ParameterizedTest
    @DisplayName("식당 메뉴에 없는 메뉴를 주문할 경우 예외가 발생한다.")
    fun menuSelectionTest(input: String) {
        assertThrows<IllegalArgumentException> {
            validateMenuSelection(input)
        }
    }

    @ValueSource(strings = ["1.5","2개","four"])
    @ParameterizedTest
    @DisplayName("메뉴의 개수가 정수가 아닌 경우 예외가 발생한다.")
    fun menuQuantityTest(input: String) {
        assertThrows<IllegalArgumentException> {
            validateMenuNumber(input)
        }
    }

    @ValueSource(strings = ["-1","0","21","999"])
    @ParameterizedTest
    @DisplayName("주문한 메뉴의 개수가 1 ~ 20개 사이가 아닌 경우 예외가 발생한다.")
    fun menuQuantityRangeTest(input: String) {
        assertThrows<IllegalArgumentException> {
            validateMenuQuantityRange(input)
        }
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    fun menuAllDrinkTest() {
        // given
        val input = listOf("제로콜라","레드와인","샴페인")

        // when & then
        assertThrows<IllegalArgumentException> {
            validateOrderDrink(input)
        }
    }

    @ValueSource(ints = [0,21,999,-1])
    @ParameterizedTest
    @DisplayName("전체 주문한 메뉴의 개수가 1 ~ 20개 사이가 아닌 경우 예외가 발생한다.")
    fun menuTotalQuantityTest(input: Int) {
        assertThrows<IllegalArgumentException> {
            validateOrderTotal(input)
        }
    }

    @Test
    @DisplayName("메뉴가 콤마(,)를 기준으로 나눠지는지 테스트")
    fun menuParsingByCommaTest() {
        // given
        val input = "바비큐립-1,초코케이크-2,제로콜라-1"

        // when
        val result = inputParser(input)

        // then
        assertThat(result).isEqualTo(listOf("바비큐립-1","초코케이크-2","제로콜라-1"))
    }

    @Test
    @DisplayName("메뉴와 개수가 하이픈(-)을 기준으로 나눠지는지 테스트")
    fun menuParsingByHyphenTest() {
        // given
        val input = listOf("바비큐립-1","초코케이크-2")

        // when
        val result = menuAndQuantityParser(input)

        // then
        assertThat(result).isEqualTo(listOf(Order("바비큐립","1"), Order("초코케이크","2")))
    }
}