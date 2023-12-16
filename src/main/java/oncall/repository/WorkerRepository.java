package oncall.repository;
import oncall.domain.Holiday;
import oncall.domain.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerRepository {
    private final List<String> ALL_WEEKDAYS = List.of("월", "화", "수", "목", "금", "토", "일");
    private final List<String> WEEKDAY = List.of("월", "화", "수", "목", "금");
    private final List<String> WEEKEND  = List.of("토", "일");
    private final String WEEKDAY_FORMAT = "평일";
    private final String WEEKEND_FORMAT = "휴일";
    private final String FORMAT = "%d월 %d일 %s %s";

    private List<Worker> weekday = new ArrayList<>();
    private List<Worker> weekend = new ArrayList<>();
    private int weekdayIndex = 0;
    private int weekendIndex = 0;

    private int month = 0;
    private int dateOfWeekIndex = 0;
    private int date = 0;
    private int endDate = 0;

    public void addWeekdayWorker(String worker) {
        weekday.add(new Worker(worker));
    }
    public void addWeekendWorker(String worker) {
        weekend.add(new Worker(worker));
    }

    public List<String> calculateEmergencyWorkerAssignment(int month, String dateOfWeek, int endDate) {
        this.month = month;
        this.dateOfWeekIndex = ALL_WEEKDAYS.indexOf(dateOfWeek);
        this.date = 1;

        List<Worker> result = new ArrayList<>();
        List<String> resultFormat = new ArrayList<>();
        while (this.date <= endDate) {
            if (weekdayIndex == weekday.size() || weekendIndex == weekend.size()) {
                break;
            }
            if (isWeekday(dateOfWeekIndex, month, date)) {
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
                resultFormat.add(String.format(FORMAT, this.month, this.date, ALL_WEEKDAYS.get(this.dateOfWeekIndex), weekday.get(weekdayIndex).getName()));

                setNextDayAndDateOfWeek(WEEKDAY_FORMAT);
                continue;
            }
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
            if (Holiday.isInHoliday(this.month, this.date)) {
                dayOfWeek = dayOfWeek+"(휴일)";
            }
            result.add(weekend.get(weekendIndex));
            resultFormat.add(String.format(FORMAT, this.month, this.date, dayOfWeek, weekend.get(weekendIndex).getName()));
            setNextDayAndDateOfWeek(WEEKEND_FORMAT);
        }

        return resultFormat;
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
