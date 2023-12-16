package oncall.repository;
import oncall.domain.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerRepository {
    private List<Worker> weekday = new ArrayList<>();
    private List<Worker> weekend = new ArrayList<>();
    private int weekdayIndex = 0;
    private int weekendIndex = 0;
    public void addWeekdayWorker(String worker) {
        weekday.add(new Worker(worker));
    }
    public void addWeekendWorker(String worker) {
        weekend.add(new Worker(worker));
    }


}
