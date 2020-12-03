package utils;

import java.util.*;
import java.util.stream.Collectors;
import model.Task;
import model.WorkLog;

public class DataProvider {

    public List<WorkLog> getLogs() {
        FileReader fileReader = new FileReader();
        List<WorkLog> logList = fileReader.provideWorkLogList();
        List<Task> taskList = getTasks();
        if (!logList.isEmpty()) {
            logList.forEach(l -> l.setTask(getTaskById(taskList, l.getTaskID())));
        }
        return logList;
    }

    public List<Task> getTasks() {
        FileReader fileReader = new FileReader();
        List<Task> taskList = fileReader.provideTaskList();
        if (!taskList.isEmpty()) {
            taskList.forEach(t -> t.setChildrenTasks(getChildTasks(taskList, t.getId())));
        }
        return taskList;
    }

    private Task getTaskById(List<Task> taskList, Long taskId) {
        Optional<Task> task = taskList.stream().filter(t -> t.getId().equals(taskId)).findAny();
        return task.orElse(null);
    }

    private List<Task> getChildTasks(List<Task> taskList, Long parentId) {
        return taskList.stream().filter(t -> t.getParent().equals(parentId))
                .collect(Collectors.toList());
    }

}
