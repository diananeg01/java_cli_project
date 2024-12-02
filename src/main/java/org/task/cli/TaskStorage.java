package org.task.cli;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TaskStorage {
    private static final String FILE_NAME = "tasks.json";

    public static JSONArray loadTasks() {
        File file = new File(FILE_NAME);
        JSONParser parser = new JSONParser();
        if (!file.exists()) {
            return new JSONArray();
        }

        try (FileReader reader = new FileReader(file)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new JSONArray();
        }
        return new JSONArray();
    }

    public static void saveTasks(JSONArray tasks) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(tasks.toJSONString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
