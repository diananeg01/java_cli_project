package org.task.cli;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TaskStorage {
    private static final String FILE_NAME = "tasks.json";

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String content = jsonBuilder.toString().trim();
            if (content.length() < 2) return tasks;
            content = content.substring(1, content.length() - 1); // remove [ and ]
            String[] taskJsons = content.split("(?<=\\}),"); // split by }, but keep }

            for (String taskJson : taskJsons) {
                taskJson = taskJson.trim();
                if (taskJson.endsWith(",")) {
                    taskJson = taskJson.substring(0, taskJson.length() - 1);
                }
                tasks.add(Task.fromJson(taskJson));
            }

        } catch (IOException e) {
            System.out.println("Error reading task file: " + e.getMessage());
        }

        return tasks;
    }

    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write("  " + tasks.get(i).toJson());
                if (i < tasks.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error saving task file: " + e.getMessage());
        }
    }
}
