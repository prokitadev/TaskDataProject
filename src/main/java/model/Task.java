package model;

import java.util.ArrayList;
import java.util.List;

public class Task {

    Long id; //id of task
    String project; //project key
    String category; //task category
    Long parent; // id of parent task if parent > -1

    List<Task> childrenTasks = new ArrayList<>();

    public enum TaskCategory {
        EPIC, STORY, TASK, SUBTASK;

        public static TaskCategory valueOfCategoryName(String category) {
            return switch (category) {
                case "Epic" -> EPIC;
                case "Story" -> STORY;
                case "Task" -> TASK;
                case "Sub-task" -> SUBTASK;
                default -> null;
            };
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public TaskCategory getTaskCategory() {
        return TaskCategory.valueOfCategoryName(category);
    }

    public void setTaskCategory(TaskCategory category) {
        this.category = category.toString();
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Task> getChildrenTasks() {
        return childrenTasks;
    }

    public void setChildrenTasks(List<Task> childTasks) {
        this.childrenTasks = childTasks;
    }
}
