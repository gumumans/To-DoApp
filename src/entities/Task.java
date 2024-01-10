package entities;

import enums.Priority;
import enums.Status;
import exceptions.StatusException;

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


    public String getDescription() {
        return description;
    }

    public LocalDate getCompletionDate() {
        return completionDate.toLocalDate();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Status getStatusObj() {
        return statusObj;
    }

    public String getStatus() {
        return status;
    }

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

    public void statusDone() {
        try {
            statusObj.changeStatusDone(this);
        } catch (StatusException e) {
            System.err.println(e.getMessage());
        }
    }

    public Priority getPriority() {
        return priority;
    }


    public void statusInProgress() {
        try {
            statusObj.changeStatusInProgress(this);
        } catch (StatusException e) {
            System.err.println(e.getMessage());
        }
    }

    public void changeDescription(String description) {
        try {
            statusObj.changeTaskDescription(this);
            this.description = description;
        } catch (StatusException e) {
            System.err.println(e.getMessage());
        }
    }


    public boolean isDeleted() {
        try {
            return statusObj.deleteTask(this);
        } catch (StatusException e) {
            System.err.println(e.getMessage());
        }
        return false;
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
