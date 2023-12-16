package christmas.view;
import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String SEPARATOR = ",";
    private static final String DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    /**
     * 정수 입력을 받고 int 파싱 후 반환하는 메소드
     */
    public static int readVisitDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        checkIsNullOrBlank(input, DATE);
        return parseToInt(input, DATE);
    }

    /**
     * separator 로 구분된 문자열 입력을 받고 split 후 반환하는 메소드
     */
    public static List<String> readCourseLevelMission() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        checkIsNullOrBlank(input, ORDER);
        return splitBySeparator(input);
    }

    private static void checkIsNullOrBlank(String input, String message) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private static int parseToInt(String input, String message) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private static List<String> splitBySeparator(String input) {
        List<String> result = Arrays.asList(input.split(SEPARATOR));
        for (String splitInput : result) {
            checkIsNullOrBlank(splitInput, ORDER);
        }
        return result;
    }
}
