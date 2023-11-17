package christmas.utils

object Constants {
    const val WEEK = 7
    const val CHRISTMAS = 25
    private const val MONTH_TWELVE = 12
    const val SPECIAL_DISCOUNT_AMOUNT = 1_000
    const val D_DAY_DISCOUNT_MIN = 900
    const val GIFT_APPLICABLE_AMOUNT = 120_000
    const val EVENT_APPLICABLE_AMOUNT = 10_000
    const val MAX_ORDER_PRICE = 1_195_000

    const val HYPHEN = "-"
    const val COMMA = ","
    const val PATTERN = "^[^,]+-\\d+$"

    const val DATE_MESSAGE = "안녕하세요! 우테코 식당 ${MONTH_TWELVE}월 이벤트 플래너입니다.\n" +
            "${MONTH_TWELVE}월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
    const val ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
    const val START_MESSAGE = "${MONTH_TWELVE}월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"
    const val ORDER_MENU_MESSAGE = "<주문 메뉴>"
    const val PRE_DISCOUNT_TOTAL_MESSAGE = "<할인 전 총주문 금액>"
    const val GIFT_MENU_MESSAGE = "<증정 메뉴>"
    const val BENEFIT_DETAILS_MESSAGE = "<혜택 내역>"
    const val BENEFIT_AMOUNT_TOTAL_MESSAGE = "<총혜택 금액>"
    const val AFTER_DISCOUNT_TOTAL_MESSAGE = "<할인 후 예상 결제 금액>"
    const val DECEMBER_EVENT_BADGE = "<${MONTH_TWELVE}월 이벤트 배지>"

    const val NO_EVENT = "없음"
    const val NO_BENEFIT = 0
    const val EVENT_AMOUNT = "%s원"
}