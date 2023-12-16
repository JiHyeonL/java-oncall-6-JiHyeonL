package oncall.view;

import java.util.List;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    /**
     * 에러 메세지 출력 메소드
     */
    public static void writeErrorMessage(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void writeWorkerResult(String workersInfo) {
        System.out.println("\n" + workersInfo);
    }



}
