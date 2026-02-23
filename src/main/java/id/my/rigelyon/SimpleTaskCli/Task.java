package id.my.rigelyon.SimpleTaskCli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final LocalDateTime createdAt;
    private final int id;
    private static int lastId = 1;
    private String description;
    private Status status;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.id = lastId++;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        switch (status) {
            case "todo":
                this.status = Status.TODO;
                break;
            case "done":
                this.status = Status.DONE;
                break;
            case "in-progress":
                this.status = Status.IN_PROGRESS;
                break;
            default:
                System.err.println("Invalid status:" + status);
        }
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson() {
        return "{\"id\":" + id + ", \"status\":\"" + status.toString() + "\", \"description\": \"" + description.strip() +
                "\", \"createdAt\": \"" + formatter.format(createdAt) + "\", \"updatedAt\": \"" + formatter.format(updatedAt) + "\"}";
    }

    @Override
    public String toString() {
        return "id: " + id + ", status: " + status.toString() + ", description: " + description.strip() +
                ", createdAt: " + formatter.format(createdAt) + ", updatedAt: " + formatter.format(updatedAt);
    }
}
