package org.task.cli;

import java.time.LocalDateTime;
import java.util.List;

public class TaskManager {
    public static void addTask(String description) {
        List<Task> tasks = TaskStorage.loadTasks();
        int nextId = tasks.stream().mapToInt(t -> t.id).max().orElse(0) + 1;
        Task newTask = new Task(nextId, description);
        tasks.add(newTask);
        TaskStorage.saveTasks(tasks);
        System.out.println("Task added successfully (ID: " + nextId + ")");
    }

    public static void updateTask(int taskId, String newDescription) {
        List<Task> tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (task.id == taskId) {
                task.description = newDescription;
                task.updatedAt = LocalDateTime.now().toString();
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

    private static void changeTaskStatus(int taskId, String newStatus) {
        List<Task> tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (task.id == taskId) {
                task.status = newStatus;
                task.updatedAt = LocalDateTime.now().toString();
                TaskStorage.saveTasks(tasks);
                System.out.println("Task marked as " + newStatus + " (ID: " + taskId + ")");
            }
        });
        System.out.println("Task not found (ID: " + taskId + ")");
    }

    public static void listTasks(String status) {
        List<Task> tasks = TaskStorage.loadTasks();
        tasks.forEach(task -> {
            if (status == null || task.status.equals(status)) {
                System.out.println(task.id + ". " + task.description + " [" + task.status + "]");
            }
        });
    }

    public static void listAllTasks() {
        listTasks(null);  // List all tasks
    }
}
