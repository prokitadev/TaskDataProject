package utils;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import model.Task;
import model.WorkLog;

public class StatsCalculator {

    public Long calculateTimeForTask(List<WorkLog> data, Task task) {
        return calculateTimeForTaskAndChildren(data, task, calculateTimeForTaskId(data, task.getId()));
    }

    public Long calculateTimeForProject(List<WorkLog> data, String projectName) {
        return data.stream().filter(l -> l.getTask().getProject().equals(projectName))
                .mapToLong(WorkLog::getTimeLogged).sum();
    }

    // All levels tasks
    public Long countTasksForProject(List<Task> data, String projectName) {
        return data.stream().filter(t -> t.getProject().equals(projectName))
                .mapToLong(Task::getId).distinct().count();
    }

    public Long calculateTimeForUser(List<WorkLog> data, String userName) {
        return data.stream().filter(l -> l.getAuthor().equals(userName))
                .mapToLong(WorkLog::getTimeLogged).sum();
    }

    public Map<String, Long> calculateTimeForEpicWithProjectName(List<WorkLog> data, Task epic) {
        List<Long> epicWithChildren = getIdsTaskAndChildren(data, epic);
        return data.stream()
                .filter(l -> epicWithChildren.stream().anyMatch(id -> id.equals(l.getTaskID())))
                .collect(Collectors.groupingBy(
                l -> l.getTask().getProject(),
                Collectors.summingLong(WorkLog::getTimeLogged)));
    }

    //  If logged time is not start or end of working hours, but time from in this same day.
    public Long calculateTimeLoggedInPeriod(List<WorkLog> data, LocalDate startDate,
            LocalDate endDate) {
        Long startDateMillis = startDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        Long endDateMillis = endDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        return data.stream()
                .filter(l -> (l.getDate() >= startDateMillis
                        && l.getDate() < endDateMillis))
                .mapToLong(WorkLog::getTimeLogged).sum();
    }

    public Long calculateTimeLoggedInPeriodAndUser(List<WorkLog> data, LocalDate startDate,
            LocalDate endDate, String user) {
        Long startDateMillis = startDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        Long endDateMillis = endDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        return data.stream()
                .filter(l -> (l.getDate() >= startDateMillis
                        && l.getDate() < endDateMillis
                        && l.getAuthor().equals(user)))
                .mapToLong(WorkLog::getTimeLogged).sum();
    }

    private Long calculateTimeForTaskAndChildren(List<WorkLog> data, Task task, Long time) {
        List<Task> children = task.getChildrenTasks();
        if (!children.isEmpty()) {
            for (Task t : children) {
                time += calculateTimeForTaskId(data, t.getId());
                time = calculateTimeForTaskAndChildren(data, t, time);
            }
        }
        return time;
    }

    private Long calculateTimeForTaskId(List<WorkLog> data, Long taskId) {
        return data.stream().filter(l -> l.getTaskID().equals(taskId))
                .mapToLong(WorkLog::getTimeLogged).sum();
    }

    private List<Long> getIdsTaskAndChildren(List<WorkLog> data, Task task) {
        List<Task> children = task.getChildrenTasks();
        List<Long> ids = new ArrayList<>();
        ids.add(task.getId());
        if (!children.isEmpty()) {
            for (Task t : children) {
                ids.add(t.getId());
                ids.addAll(getIdsTaskAndChildren(data, t));
            }
        }
        return ids;
    }
}
