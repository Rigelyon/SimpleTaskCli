package id.my.rigelyon.SimpleTaskCli;

import java.nio.file.Path;
import java.util.List;

public class TaskManager {
    private final Path FILEPATH = Path.of("Tasks.json");
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = JsonHandler.loadFromFile(FILEPATH);
    }

    public void saveTasks() {
        JsonHandler.saveToFile(FILEPATH, tasks);
    }

    public void showTasks() {

    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Added new task: " + newTask);
    }

    public void editTask(int id) {

    }

    public void deleteTask(int id) {
        try {
            tasks.removeIf(task -> task.getId() == id);
        } catch (Exception e) {
            System.err.println("Error while trying to remove task: " + e);
        }
    }

}
