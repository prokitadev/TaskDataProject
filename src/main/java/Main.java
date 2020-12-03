import java.time.LocalDate;
import java.util.List;
import model.Task;
import model.WorkLog;
import utils.DataProvider;
import utils.StatsCalculator;

public class Main {

    public static void main(String[] args) {

        DataProvider dataProvider = new DataProvider();
        List<WorkLog> logs = dataProvider.getLogs();
        List<Task> tasks = dataProvider.getTasks();
        StatsCalculator calculator = new StatsCalculator();

        System.out.println(calculator.calculateTimeForTask(logs, logs.get(88).getTask()));
        System.out.println(calculator.calculateTimeForProject(logs, "B"));
        System.out.println(calculator.countTasksForProject(tasks, "B"));
        System.out.println(calculator.calculateTimeForUser(logs, "AMELIA"));
        System.out.println(calculator.calculateTimeForEpicWithProjectName(logs, logs.get(47).getTask()));
        System.out.println(calculator.calculateTimeLoggedInPeriod(logs,
                LocalDate.of(2020,9,12), LocalDate.of(2020,9,13)));
        System.out.println(calculator.calculateTimeLoggedInPeriodAndUser(logs,
                LocalDate.of(2020,9,12), LocalDate.of(2020,9,15), "ANTONI"));

    }
}
