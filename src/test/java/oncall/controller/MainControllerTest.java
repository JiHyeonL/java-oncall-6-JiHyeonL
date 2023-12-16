package oncall.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import oncall.Application;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 기능_테스트() {
        assertSimpleTest(() -> {
            run("6,수",
                    "A,B,C,D,E",
                    "C,F,G,H,I,J"
            );
            assertThat(output()).contains(
                    "6월 1일 수 A" + LINE_SEPARATOR,
                    "6월 2일 목 B" + LINE_SEPARATOR,
                    "6월 3일 금 C" + LINE_SEPARATOR,
                    "6월 4일 토 F" + LINE_SEPARATOR,
                    "6월 5일 일 C" + LINE_SEPARATOR,
                    "6월 6일 월(휴일) G" + LINE_SEPARATOR,
                    "6월 7일 화 D" + LINE_SEPARATOR,
                    "6월 8일 수 E" + LINE_SEPARATOR,
                    "6월 9일 목 A" + LINE_SEPARATOR
            );
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}