package entities;

import enums.Priority;
import enums.Status;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private Date completionDate;
    private Priority priority;
    private Date createdDate;
    private transient Status statusObj;
    private String status;

    protected Task() {
        this.statusObj = Status.NEW;
        this.status = statusObj.toString();
        this.createdDate = Date.valueOf(LocalDate.now());
    }

    public Task(String title, String description, LocalDate completionDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.completionDate = Date.valueOf(completionDate);
        this.priority = priority;

        this.statusObj = Status.NEW;
        this.status = statusObj.toString();
        this.createdDate = Date.valueOf(LocalDate.now());
    }

    public void setStatusObj(Status statusObj) {
        this.statusObj = statusObj;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "{\"Task\":{"
                + "        \"title\":\"" + title + "\""
                + ",         \"description\":\"" + description + "\""
                + ",         \"completionDate\":" + completionDate
                + ",         \"createdDate\":" + createdDate
                + ",         \"statusObj\":\"" + statusObj + "\""
                + ",         \"status\":\"" + status + "\""
                + ",         \"priority\":\"" + priority + "\""
                + "}}";
    }


    public static class TaskBuilder {
        private final Task newTask;

        public TaskBuilder() {
            this.newTask = new Task();
        }

        public TaskBuilder title(String title) {
            newTask.title = title;
            return this;
        }

        public TaskBuilder description(String desc) {
            this.newTask.description = desc;
            return this;
        }

        public TaskBuilder completionDate(LocalDate date) {
            newTask.completionDate = Date.valueOf(date);
            return this;
        }

        public TaskBuilder priority(Priority priority) {
            newTask.priority = priority;
            return this;
        }

        public Task build() {
            return newTask;
        }
    }
}
