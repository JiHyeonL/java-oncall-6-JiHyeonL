package oncall.controller;

import oncall.service.EmergencyService;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class MainController {
    private final EmergencyService emergencyService;

    public MainController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    public void run() {
        // 입력
        setEmergencyDateWithValidation();
        setEmergencyWeekdayWorkerWithValidation();
        setEmergencyWeekendDateWithValidation();

        emergencyService.calculateEmergencyWorkerAssignment();
        OutputView.writeWorkerResult(emergencyService.getAssignedWorker());
    }


    private void setEmergencyDateWithValidation() {
        while (true) {
            try {
                List<String> date = InputView.readDate();
                emergencyService.setEmergencyDate(date.get(0), date.get(1));
                break;
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e.getMessage());
            }
        }
    }

    private void setEmergencyWeekdayWorkerWithValidation() {
        while (true) {
            try {
                List<String> weekdayWorker = InputView.readWeekdayWorker();
                emergencyService.setEmergencyWeekdayWorker(weekdayWorker);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e.getMessage());
            }
        }
    }

    private void setEmergencyWeekendDateWithValidation() {
        while (true) {
            try {
                List<String> weekendWorker = InputView.readWeekendWorker();
                emergencyService.setEmergencyWeekendWorker(weekendWorker);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e.getMessage());
            }
        }
    }


}
