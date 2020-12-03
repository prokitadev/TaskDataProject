package model;

public class WorkLog {

    String author; //author
    Long timeLogged; //time spent on
    Long date; //the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this date.
    Long taskID; //id of task where time is logged

    Task task;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getTimeLogged() {
        return timeLogged;
    }

    public void setTimeLogged(Long timeLogged) {
        this.timeLogged = timeLogged;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskId(Long taskID) {
        this.taskID = taskID;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
