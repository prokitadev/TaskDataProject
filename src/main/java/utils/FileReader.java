package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.WorkLog;

public class FileReader {

    List<WorkLog> provideWorkLogList() {
        List<WorkLog> workLogs = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/worklogs_Java.txt"));
            workLogs = new Gson().fromJson(reader, new TypeToken<List<WorkLog>>(){}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return workLogs;
    }

    List<Task> provideTaskList() {
        List<Task> tasks = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/tasks_java.txt"));
            tasks = new Gson().fromJson(reader, new TypeToken<List<Task>>(){}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tasks;
    }
}
