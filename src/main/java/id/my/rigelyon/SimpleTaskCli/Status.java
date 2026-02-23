package id.my.rigelyon.SimpleTaskCli;

public enum Status {
    TODO("todo"),
    DONE("done"),
    IN_PROGRESS("in-progress"),;

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
