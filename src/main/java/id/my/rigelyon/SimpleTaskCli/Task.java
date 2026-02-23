package id.my.rigelyon.SimpleTaskCli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 1;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

    public static Task fromJson(String jsonContent) {
        String cleanString = jsonContent.replace("\n", "")
                .replace("{", "")
                .replace("}", "");
        String[] parts = cleanString.split(",");

        int id = 0;
        Status status = null;
        String description = "";
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        for (String part : parts) {
            String[] split = part.split(": ");
            String key = split[0].trim().replace("\"", "");
            String value = split[1].trim().replace("\"", "");

            if (key.equals("id")) id = Integer.parseInt(value);
            if (key.equals("description")) description = value;
            if (key.equals("status")) status = Status.fromLabel(value);
            if (key.equals("createdAt")) createdAt = LocalDateTime.parse(value, formatter);
            if (key.equals("updatedAt")) updatedAt = LocalDateTime.parse(value, formatter);
        }
        Task newTask = new Task(description);

        newTask.id = id;
        newTask.status = status;
        newTask.description = description;
        newTask.updatedAt = updatedAt;
        newTask.createdAt = createdAt;

        if (id >= lastId) {
            lastId = id;
        }
        return newTask;
    }

    public String toJson() {
        return "{\"id\": " + id + ", \"status\": \"" + status.toString() + "\", \"description\": \"" + description.strip() +
                "\", \"createdAt\": \"" + formatter.format(createdAt) + "\", \"updatedAt\": \"" + formatter.format(updatedAt) + "\"}";
    }

    @Override
    public String toString() {
        return "id: " + id + ", status: " + status.toString() + ", description: " + description.strip() +
                ", createdAt: " + formatter.format(createdAt) + ", updatedAt: " + formatter.format(updatedAt);
    }
}
