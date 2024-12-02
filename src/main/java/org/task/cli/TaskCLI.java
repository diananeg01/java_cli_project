package org.task.cli;

public class TaskCLI {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("task-cli <command> [options]");
        } else {

            String cmd = args[0];
            switch (cmd) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Please provide a task description.");
                    } else {
                        TaskManager.addTask(args[1]);
                    }
                    break;
                case "update":
                    if (args.length < 3) {
                        System.out.println("Please provide a task ID and new description.");
                    } else {
                        TaskManager.updateTask(Integer.parseInt(args[1]), args[2]);
                    }
                    break;
                case "delete":
                    // Logic for deleting a task
                    break;
                case "mark-in-progress":
                    TaskManager.markTaskInProgress(Integer.parseInt(args[1]));
                    break;
                case "mark-done":
                    TaskManager.markTaskDone(Integer.parseInt(args[1]));
                    break;
                case "list":
                    if (args.length == 1) {
                        TaskManager.listAllTasks();
                    } else {
                        TaskManager.listTasks(args[1]);
                    }
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
