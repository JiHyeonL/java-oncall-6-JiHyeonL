package oncall;

public class AssignmentFormat {
    private int month;
    private int date;
    private String dateOfWeek;
    private String name;

    public AssignmentFormat(int month, int date, String dateOfWeek, String name) {
        this.month = month;
        this.date = date;
        this.dateOfWeek = dateOfWeek;
        this.name = name;
    }

    public String getAssignmentFormat() {
        return String.format("%d월 %d일 %s %s", month, date, dateOfWeek, name);
    }
}
