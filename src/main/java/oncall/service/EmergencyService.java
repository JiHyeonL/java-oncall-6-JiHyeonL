package oncall.service;

import oncall.repository.WorkerRepository;

import java.util.List;

public class EmergencyService {
    private final WorkerRepository workerRepository;

    private int month = 0;
    private String dateOfWeek = "";

    public EmergencyService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void setEmergencyDate(String month, String dateOfWeek) {
        this.month = parseToInt(month);
        this.dateOfWeek = dateOfWeek;
    }

    public void setEmergencyWeekdayWorker(List<String> weekdayWorker) {
        for (String worker : weekdayWorker) {
            workerRepository.addWeekdayWorker(worker);
        }
    }

    public void setEmergencyWeekendWorker(List<String> weekdayWorker) {
        for (String worker : weekdayWorker) {
            workerRepository.addWeekdayWorker(worker);
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


}
