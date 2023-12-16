package oncall.domain;

import java.time.LocalDate;

public enum Holiday {
    신정(1, 1),
    삼일절(3, 1),
    어린이날( 5, 5),
    현충일(6, 6),
    광복절(8, 15),
    개천절(10, 3),
    한글날(10, 9),
    성탄절(12, 25);

    private final int month;
    private final int date;

    Holiday(int month, int date) {
        this.month = month;
        this.date = date;
    }

    public static boolean isInHoliday(int month, int date) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.month == month && holiday.date == date) {
                return true;
            }
        }
        return false;
    }
}
