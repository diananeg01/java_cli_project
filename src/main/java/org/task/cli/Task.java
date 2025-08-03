package org.task.cli;

import java.time.LocalDateTime;

public class Task {
    public int id;
    public String description;
    public String status;
    public String createdAt;
    public String updatedAt;

    public Task(int id, String description, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = this.createdAt;
    }

    public String toJson() {
        return String.format(
                "{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
                id, description, status, createdAt, updatedAt
        );
    }

    public static Task fromJson(String json) {
        Task task = new Task(0, "");
        task.id = Integer.parseInt(getJsonValue(json, "id"));
        task.description = getJsonValue(json, "description");
        task.status = getJsonValue(json, "status");
        task.createdAt = getJsonValue(json, "createdAt");
        task.updatedAt = getJsonValue(json, "updatedAt");
        return task;
    }

    private static String getJsonValue(String json, String key) {
        String match = "\"" + key + "\":";
        int start = json.indexOf(match) + match.length();
        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf('"', start);
            return json.substring(start, end);
        } else {
            int end = json.indexOf(',', start);
            if (end == -1) end = json.indexOf('}', start);
            return json.substring(start, end).trim();
        }
    }
}
