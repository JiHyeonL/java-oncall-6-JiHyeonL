package oncall;

import oncall.controller.MainController;
import oncall.repository.WorkerRepository;
import oncall.service.EmergencyService;

public class Application {
    public static void main(String[] args) {
        WorkerRepository workerRepository = new WorkerRepository();
        EmergencyService emergencyService = new EmergencyService(workerRepository);
        MainController mainController = new MainController(emergencyService);
        mainController.run();
    }
}
