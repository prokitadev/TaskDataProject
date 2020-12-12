package model;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private Long id; //id of task
    private String project; //project key
    private String category; //task category
    private Long parent; // id of parent task if parent > -1

    public Task(Long id, String project, String category, Long parent) {
        this.id = id;
        this.project = project;
        this.category = category;
        this.parent = parent;
    }

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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Task) {
            Task o = (Task) obj;
            return id.equals(o.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ",project=" + project + ",category="
                + category + ",parent=" + parent + "}";
    }
}
