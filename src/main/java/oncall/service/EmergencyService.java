package oncall.service;
import oncall.AssignmentFormat;
import oncall.domain.Month;

import oncall.repository.WorkerRepository;
import java.util.ArrayList;
import java.util.List;

public class EmergencyService {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final List<String> Weekdays = List.of("월", "화", "수", "목", "금", "토", "일");

    private final int MIN_WORKER_SIZE = 5;
    private final int MAX_WORKER_SIZE = 35;

    private final WorkerRepository workerRepository;
    private List<AssignmentFormat> assignedWorker;

    private Month month;
    private String dateOfWeek = "";


    public EmergencyService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void setEmergencyDate(String month, String dateOfWeek) {
        validateWeekdays(dateOfWeek);

        this.month = Month.getMonthFormat(parseToInt(month));
        this.dateOfWeek = dateOfWeek;
    }

    public void validateWeekdays(String dateOfWeek) {
        if (!Weekdays.contains(dateOfWeek)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


    public void setEmergencyWeekdayWorker(List<String> weekdayWorker) {
        validateWorkerSize(weekdayWorker);
        workerRepository.addAllWeekdayWorker(weekdayWorker);
    }

    public void setEmergencyWeekendWorker(List<String> weekendWorker) {
        validateWorkerSize(weekendWorker);
        workerRepository.addAllWeekendWorker(weekendWorker);
    }

    private void validateWorkerSize(List<String> workers) {
        if (workers.size() < MIN_WORKER_SIZE || workers.size() > MAX_WORKER_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 인원입니다. 다시 입력해 주세요.");
        }
    }

    public void calculateEmergencyWorkerAssignment() {
        this.assignedWorker = workerRepository.calculateEmergencyWorkerAssignment(month, dateOfWeek);
    }

    public String getAssignedWorker() {
        List<String> assignmentResult = new ArrayList<>();

        for (AssignmentFormat assignmentWorker : assignedWorker) {
            assignmentResult.add(assignmentWorker.getAssignmentFormat());
        }
        return String.join(LINE_SEPARATOR, assignmentResult);
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


}
