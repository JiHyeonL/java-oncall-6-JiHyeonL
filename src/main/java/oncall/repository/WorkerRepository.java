package oncall.repository;
import oncall.AssignmentFormat;
import oncall.domain.Holiday;
import oncall.domain.Month;
import oncall.domain.Worker;

import java.util.*;

public class WorkerRepository {
    private final List<String> ALL_WEEKDAYS = List.of("월", "화", "수", "목", "금", "토", "일");
    private final List<String> WEEKDAY = List.of("월", "화", "수", "목", "금");
    private final String WEEKDAY_FORMAT = "평일";
    private final String WEEKEND_FORMAT = "휴일";
    private final String WEEKDAY_HOLIDAY = "(휴일)";

    private List<Worker> weekday = new ArrayList<>();
    private List<Worker> weekend = new ArrayList<>();
    private int weekdayIndex = 0;
    private int weekendIndex = 0;

    private List<Worker> result = new ArrayList<>();
    private int month = 0;
    private int dateOfWeekIndex = 0;
    private int date = 0;

    public void addAllWeekdayWorker(List<String> workers) {
        validate(workers);

        for (String worker : workers) {
            weekday.add(new Worker(worker));
        }
    }
    public void addAllWeekendWorker(List<String> workers) {
        validate(workers);

        for (String worker : workers) {
            weekend.add(new Worker(worker));
        }
    }

    private void validate(List<String> workers) {
        Set<String> noDuplicate = new HashSet<>(workers);

        if (workers.size() != noDuplicate.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

    public List<AssignmentFormat> calculateEmergencyWorkerAssignment(Month month, String dateOfWeek) {
        this.month = month.getMonth();
        this.dateOfWeekIndex = ALL_WEEKDAYS.indexOf(dateOfWeek);
        this.date = 1;

        List<AssignmentFormat> resultFormat = new ArrayList<>();
        while (this.date <= month.getLastDate()) {
            if (isWeekday(dateOfWeekIndex, this.month, date)) {
                resultFormat.add(addEmergencyWeekdayWorker());
                setNextDayAndDateOfWeek(WEEKDAY_FORMAT);
                continue;
            }
            resultFormat.add(addEmergencyWeekendWorker());
            setNextDayAndDateOfWeek(WEEKEND_FORMAT);
        }

        return resultFormat;
    }

    private AssignmentFormat addEmergencyWeekdayWorker() {
        Worker worker = weekday.get(weekdayIndex);

        if (!result.isEmpty()) {
            if (result.get(result.size() - 1).isSameWorker(worker)) {
                Worker nowWorker = weekday.get(weekdayIndex);
                Worker nextWorker = weekday.get(weekdayIndex + 1);  // 인덱스 넘을 경우 확인
                weekday.set(weekdayIndex, nextWorker);
                weekday.set(weekdayIndex + 1, nowWorker);
            }
        }
        result.add(weekday.get(weekdayIndex));
        return new AssignmentFormat(this.month, this.date, ALL_WEEKDAYS.get(this.dateOfWeekIndex), weekday.get(weekdayIndex).getName());
    }

    private AssignmentFormat addEmergencyWeekendWorker() {
        Worker worker = weekend.get(weekendIndex);
        if (!result.isEmpty()) {
            if (result.get(result.size() - 1).isSameWorker(worker)) {
                Worker nowWorker = weekend.get(weekendIndex);
                Worker nextWorker = weekend.get(weekendIndex + 1);  // 인덱스 넘을 경우 확인
                weekend.set(weekendIndex, nextWorker);
                weekend.set(weekendIndex + 1, nowWorker);
            }
        }
        String dayOfWeek = ALL_WEEKDAYS.get(this.dateOfWeekIndex);
        if (Holiday.isInHoliday(this.month, this.date) && WEEKDAY.contains(ALL_WEEKDAYS.get(dateOfWeekIndex))) {
            dayOfWeek = dayOfWeek + WEEKDAY_HOLIDAY;
        }
        result.add(weekend.get(weekendIndex));
        return new AssignmentFormat(this.month, this.date, dayOfWeek, weekend.get(weekendIndex).getName());
    }


    private void setNextDayAndDateOfWeek(String dateType) {
        dateOfWeekIndex = (dateOfWeekIndex + 1) % ALL_WEEKDAYS.size();
        date += 1;
        if (dateType.equals(WEEKDAY_FORMAT)) {
            weekdayIndex = (weekdayIndex + 1) % weekday.size();
            return;
        }
        weekendIndex = (weekendIndex + 1) % weekend.size();
    }

    private boolean isWeekday(int dateOfWeekIndex, int month, int date) {
        if (WEEKDAY.contains(ALL_WEEKDAYS.get(dateOfWeekIndex)) && !Holiday.isInHoliday(month, date)) {
            return true;
        }
        return false;
    }




}
