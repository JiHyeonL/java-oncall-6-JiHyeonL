package oncall.view;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    /**
     * 에러 메세지 출력 메소드
     */
    public static void writeErrorMessage(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void writeEventAnnounce(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",date));
    }

    public static void writeOrderMenu(String orderFormat) {
        System.out.println("\n<주문 메뉴>");
        System.out.println(orderFormat);
    }

    public static void writeTotalOrderAmount(String priceFormat) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(priceFormat);
    }

    public static void writeGiveawayMenu(String foodName) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(foodName);
    }

    public static void writeTotalBenefitDetail(String benefitFormat) {
        System.out.println("\n<혜택 내역>");
        System.out.println(benefitFormat);
    }

    public static void writeTotalBenefitAmount(String benefitAmountFormat) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(benefitAmountFormat);
    }

    public static void writeExpectPrice(String expectPriceFormat) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(expectPriceFormat);
    }

    public static void writeEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }


}
