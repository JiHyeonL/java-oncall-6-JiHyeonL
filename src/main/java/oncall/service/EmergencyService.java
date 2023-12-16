package oncall.service;

import oncall.domain.Worker;
import oncall.repository.WorkerRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class EmergencyService {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final List<String> Weekdays = List.of("월", "화", "수", "목", "금", "토", "일");
    private final List<Integer> MONTH = List.of(1,2,3,4,5,6,7,8,9,10,11,12);

    private final WorkerRepository workerRepository;
    private List<String> assignedWorker;

    private int month = 0;
    private String dateOfWeek = "";
    private int endDay = 0;


    public EmergencyService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void setEmergencyDate(String month, String dateOfWeek) {
        validateMonth(parseToInt(month));
        validateWeekdays(dateOfWeek);
        this.month = parseToInt(month);
        this.dateOfWeek = dateOfWeek;

        if (this.month == 2) {
            this.endDay = 28;
            return;
        }

        LocalDate localDate = LocalDate.of(2023, 5, 1);
        int lastDayOfMonth = localDate.lengthOfMonth();
        this.endDay = lastDayOfMonth;
    }

    public void validateMonth(int month) {
        if (!MONTH.contains(month)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateWeekdays(String dateOfWeek) {
        if (!Weekdays.contains(dateOfWeek)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


    public void setEmergencyWeekdayWorker(List<String> weekdayWorker) {
        for (String worker : weekdayWorker) {
            workerRepository.addWeekdayWorker(worker);
        }
    }

    public void setEmergencyWeekendWorker(List<String> weekdayWorker) {
        for (String worker : weekdayWorker) {
            workerRepository.addWeekendWorker(worker);
        }
    }

    public void calculateEmergencyWorkerAssignment() {
        this.assignedWorker = workerRepository.calculateEmergencyWorkerAssignment(month, dateOfWeek, endDay);
    }

    public String getAssignedWorker() {
        return String.join(LINE_SEPARATOR, assignedWorker);
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


}
