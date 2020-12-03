import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.*;
import java.util.*;
import model.Task;
import model.WorkLog;
import utils.StatsCalculator;


public class StatsCalculatorTest {

    Task epicTask = new Task();
    Task storyTask = new Task();
    Task subtaskTaskWithoutLogs = new Task();
    WorkLog log1 = new WorkLog();
    WorkLog log2 = new WorkLog();
    WorkLog log3 = new WorkLog();
    List<WorkLog> logs = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    StatsCalculator sc = new StatsCalculator();

    @Before
    public void setUp() {
        epicTask.setCategory(Task.TaskCategory.EPIC.name());
        epicTask.setId(1L);
        epicTask.setProject("A");
        storyTask.setCategory(Task.TaskCategory.STORY.name());
        storyTask.setId(2L);
        storyTask.setParent(1L);
        storyTask.setProject("B");
        subtaskTaskWithoutLogs.setCategory(Task.TaskCategory.SUBTASK.name());
        subtaskTaskWithoutLogs.setId(3L);
        subtaskTaskWithoutLogs.setParent(2L);
        subtaskTaskWithoutLogs.setProject("C");
        epicTask.setChildrenTasks(List.of(storyTask));
        log1.setTask(epicTask);
        log1.setTaskId(epicTask.getId());
        log1.setAuthor("ANNA");
        log1.setDate(LocalDateTime.of(2020,12,2, 19, 18, 44)
                .toInstant(ZoneOffset.UTC).toEpochMilli());
        log1.setTimeLogged(8L);
        log2.setTask(storyTask);
        log2.setTaskId(storyTask.getId());
        log2.setAuthor("DAMIAN");
        log2.setDate(LocalDateTime.of(2020,12,2, 15, 23, 24)
                .toInstant(ZoneOffset.UTC).toEpochMilli());
        log2.setTimeLogged(3L);
        log3.setTask(storyTask);
        log3.setTaskId(storyTask.getId());
        log3.setAuthor("ANNA");
        log3.setDate(LocalDateTime.of(2020,12,3, 5, 6, 33)
                .toInstant(ZoneOffset.UTC).toEpochMilli());
        log3.setTimeLogged(8L);
        logs.add(log1);
        logs.add(log2);
        logs.add(log3);
        tasks.add(epicTask);
        tasks.add(storyTask);
        tasks.add(subtaskTaskWithoutLogs);

    }

    @Test
    public void shouldRetujkhrnCalculateTimeForTask() {
        Long allForEpic = 19L;
        assertEquals(allForEpic,
                sc.calculateTimeForTask(logs, epicTask));
        Long allForStory = 11L;
        assertEquals(allForStory,
                sc.calculateTimeForTask(logs, storyTask));
        Long allForSubtask = 0L;
        assertEquals(allForSubtask,
                sc.calculateTimeForTask(logs, subtaskTaskWithoutLogs));
    }

    @Test
    public void shouldReturnCalculateTimeForProject() {
        Long allForA = 8L;
        assertEquals(allForA,
                sc.calculateTimeForProject(logs, "A"));
        Long allForB = 11L;
        assertEquals(allForB,
                sc.calculateTimeForProject(logs, "B"));
        Long allForC = 0L;
        assertEquals(allForC,
                sc.calculateTimeForProject(logs, "C"));
    }

    @Test
    public void shouldReturnCountTasksForProject() {
        Long allForA = 1L;
        assertEquals(allForA,
                sc.countTasksForProject(tasks, "A"));
        Long allForB = 1L;
        assertEquals(allForB,
                sc.countTasksForProject(tasks, "B"));
        Long allForC = 1L;
        assertEquals(allForC,
                sc.countTasksForProject(tasks, "C"));
    }

    @Test
    public void shouldReturnCalculateTimeForUser() {
        Long allForAnna = 16L;
        assertEquals(allForAnna,
                sc.calculateTimeForUser(logs, "ANNA"));
        Long allForDamian = 3L;
        assertEquals(allForDamian,
                sc.calculateTimeForUser(logs, "DAMIAN"));
        Long allForJan = 0L;
        assertEquals(allForJan,
                sc.calculateTimeForUser(logs, "JAN"));
    }

    @Test
    public void shouldReturnCalculateTimeForEpicWithProjectName() {
        Map<String, Long> allForEpic = new HashMap<>();
        allForEpic.put("A", 8L);
        allForEpic.put("B", 11L);
        assertEquals(allForEpic,
                sc.calculateTimeForEpicWithProjectName(logs, epicTask));
    }

    @Test
    public void shouldReturnCalculateTimeForPeriod() {
        Long allFor2Dec = 11L;
        LocalDate startDate2Dec = LocalDate.of(2020,12,2);
        LocalDate endDate2Dec = LocalDate.of(2020,12,3);
        assertEquals(allFor2Dec,
                sc.calculateTimeLoggedInPeriod(logs, startDate2Dec, endDate2Dec));
        Long allFor5Dec = 0L;
        LocalDate startDate5Dec = LocalDate.of(2020,12,5);
        LocalDate endDate5Dec = LocalDate.of(2020,12,6);
        assertEquals(allFor5Dec,
                sc.calculateTimeLoggedInPeriod(logs, startDate5Dec, endDate5Dec));
    }

    @Test
    public void shouldReturnCalculateTimeForPeriodAndUser() {
        Long allFor2DecAnna = 8L;
        LocalDate startDate2Dec = LocalDate.of(2020,12,2);
        LocalDate endDate2Dec = LocalDate.of(2020,12,3);
        assertEquals(allFor2DecAnna,
                sc.calculateTimeLoggedInPeriodAndUser(logs, startDate2Dec, endDate2Dec, "ANNA"));
    }
}
