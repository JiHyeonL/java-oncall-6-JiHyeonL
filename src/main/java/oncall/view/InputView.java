package oncall.view;
import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String SEPARATOR = ",";
    private static final String ERROR = "유효하지 않은 입력 값입니다. 다시 입력해 주세요.";
    private static final int DATE = 2;


    /**
     * 정수 입력을 받고 int 파싱 후 반환하는 메소드
     */
    public static List<String> readDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        checkIsNullOrBlank(input, ERROR);

        List<String> result = splitBySeparator(input, ERROR);
        if (result.size() != DATE) {
            throw new IllegalArgumentException(ERROR);
        }
        return result;
    }

    /**
     * separator 로 구분된 문자열 입력을 받고 split 후 반환하는 메소드
     */
    public static List<String> readWeekdayWorker() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        checkIsNullOrBlank(input, ERROR);
        return splitBySeparator(input, ERROR);
    }

    public static List<String> readWeekendWorker() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        checkIsNullOrBlank(input, ERROR);
        return splitBySeparator(input, ERROR);
    }

    private static void checkIsNullOrBlank(String input, String message) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private static List<String> splitBySeparator(String input, String message) {
        List<String> result = Arrays.asList(input.split(SEPARATOR));
        for (String splitInput : result) {
            checkIsNullOrBlank(splitInput, message);
        }
        return result;
    }
}
