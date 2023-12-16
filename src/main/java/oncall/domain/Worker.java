package oncall.domain;

public class Worker {
    private final String name;

    public Worker(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {

    }
}
