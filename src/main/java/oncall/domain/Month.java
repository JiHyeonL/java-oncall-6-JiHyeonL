package oncall.domain;

import java.util.List;

public enum Month {
    JANUARY(1, 31, List.of(Holiday.신정)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(Holiday.삼일절)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(Holiday.어린이날)),
    JUNE(6, 30, List.of(Holiday.현충일)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(Holiday.광복절)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(Holiday.개천절, Holiday.한글날)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(Holiday.성탄절));

    private final int month;
    private final int lastDate;
    private final List<Holiday> holidays;

    Month(int month, int lastDate, List<Holiday> holidays) {
        this.month = month;
        this.lastDate = lastDate;
        this.holidays = holidays;
    }

    public static Month getMonthFormat(int month) {
        for (Month m : Month.values()) {
            if (m.month == month) {
                return m;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 월입니다. 다시 입력해 주세요.");
    }

    public int getMonth() {
        return month;
    }

    public int getLastDate() {
        return lastDate;
    }
}
