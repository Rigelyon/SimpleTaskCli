package id.my.rigelyon.SimpleTaskCli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    public static void saveToFile(Path filePath, List<Task> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i).toJson());
            if (i != data.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(filePath, jsonContent);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    public static List<Task> loadFromFile(Path filePath) {
        List<Task> loadedTask = new ArrayList<>();

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                String jsonContent = Files.readString(filePath)
                        .replace("\r", "")
                        .replace("\n", "")
                        .replace("[", "")
                        .replace("]", "");
                String[] taskLists = jsonContent.split("},");

                for (String taskList : taskLists) {
                    if (!taskList.endsWith("}")) {
                        taskList = taskList + "}";
                    }
                    loadedTask.add(Task.fromJson(taskList));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e);
        }
        return loadedTask;
    }
}
