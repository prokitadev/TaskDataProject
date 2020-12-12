import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.WorkLog;

public class FileReader {

    List<WorkLog> provideLogList() {
        List<WorkLog> workLogs = new ArrayList<>();
        try {
            URL res = getClass().getClassLoader().getResource("worklogs_Java.txt");
            Reader reader = Files.newBufferedReader(Paths.get(res.toURI()));
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
            URL res = getClass().getClassLoader().getResource("tasks_java.txt");
            Reader reader = Files.newBufferedReader(Paths.get(res.toURI()));
            tasks = new Gson().fromJson(reader, new TypeToken<List<Task>>(){}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tasks;
    }
}
