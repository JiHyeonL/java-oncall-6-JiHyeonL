package oncall.domain;

import java.time.LocalDate;

public class Worker {
    private final String FORMAT = "%d월 %d일 %s %s";
    private final String name;

    public Worker(String name) {
        validate(name);
        this.name = name;
    }

    public boolean isSameWorker(Worker worker) {
        if (this.name.equals(worker.name)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {

    }
}
