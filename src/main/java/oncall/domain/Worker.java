package oncall.domain;

import java.time.LocalDate;

public class Worker {
    private final int MAX_NAME_LENGTH = 5;

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
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5글자 이하로 입력해주세요.");
        }
    }
}
