package oncall.domain;

public class Date {
    private final int month;
    private final int date;
    private final String dateOfWeek;

    public Date(int month, int date, String dateOfWeek) {
        this.month = month;
        this.date = date;
        this.dateOfWeek = dateOfWeek;
    }

    public String getAssignmentFormat() {
        return String.format("%d월 %d일 %s", month, date, dateOfWeek);
    }
}
