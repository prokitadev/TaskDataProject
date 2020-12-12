import java.time.LocalDate;
import java.util.List;
import model.Task;
import model.WorkLog;

public class Main {

    public static void main(String[] args) {

        StatisticCalculatorApp app = new StatisticCalculatorApp();

        System.out.println("Time logged for Task with id 1 = " +
                app.calculateTimeForTask(new Task(5L, "E", "Epic", -1L)));

        System.out.println("Time logged for Project A = " + app.calculateTimeForProject("A"));

        System.out.println("Time logged for Project B = " + app.countTasksForProject("B"));

        System.out.println("Time logged by user Amelia = " + app.calculateTimeForUser("AMELIA"));

        System.out.println("Time logged for Epic with project names = " +
                app.calculateTimeForEpicWithProjectName((new Task(5L, "E", "Epic", -1L))));

        System.out.println("Time logged by date = " + app.calculateTimeLoggedPerDay());

        System.out.println("Time logged in period by user Antoni = " +
                app.calculateTimeLoggedInPeriodByUser(
                LocalDate.of(2020,9,12), LocalDate.of(2020,9,15),
                        "ANTONI"));

    }
}
