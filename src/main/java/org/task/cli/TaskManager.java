package org.task.cli;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;

public class TaskManager {
    @SuppressWarnings("unchecked")
    public static void addTask(String description) {
        JSONArray tasks = TaskStorage.loadTasks();
        int id = tasks.size() + 1;

        JSONObject newTask = new JSONObject();
        newTask.put("id", id);
        newTask.put("description", description);
        newTask.put("status", "todo");
        newTask.put("createdAt", LocalDateTime.now().toString());
        newTask.put("updatedAt", LocalDateTime.now().toString());

        tasks.add(newTask);
        TaskStorage.saveTasks(tasks);

        System.out.println("Task added successfully (ID: " + id + ")");
    }

    @SuppressWarnings("unchecked")
    public static void updateTask(int taskId, String newDescription) {
        JSONArray tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (((Task) task).id == taskId) {
                ((Task) task).description = newDescription;
                ((Task) task).updatedAt = LocalDateTime.now().toString();
                TaskStorage.saveTasks(tasks);
                System.out.println("Task updated successfully (ID: " + taskId + ")");
            }
        });
        System.out.println("Task not found (ID: " + taskId + ")");
    }

    public static void markTaskInProgress(int taskId) {
        changeTaskStatus(taskId, "in-progress");
    }

    public static void markTaskDone(int taskId) {
        changeTaskStatus(taskId, "done");
    }

    @SuppressWarnings("unchecked")
    private static void changeTaskStatus(int taskId, String newStatus) {
        JSONArray tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (((Task) task).id == taskId) {
                ((Task) task).status = newStatus;
                ((Task) task).updatedAt = LocalDateTime.now().toString();
                TaskStorage.saveTasks(tasks);
                System.out.println("Task marked as " + newStatus + " (ID: " + taskId + ")");
            }
        });
        System.out.println("Task not found (ID: " + taskId + ")");
    }

    @SuppressWarnings("unchecked")
    public static void listTasks(String status) {
        JSONArray tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (status == null || ((Task) task).status.equals(status)) {
                System.out.println(((Task) task).id + ". " + ((Task) task).description + " [" + ((Task) task).status + "]");
            }
        });
    }

    public static void listAllTasks() {
        listTasks(null);  // List all tasks
    }
}
