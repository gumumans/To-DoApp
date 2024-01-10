package entities;

import enums.Priority;
import enums.Status;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate completionDate;
    private LocalDate createdDate;
    private transient Status statusObj;
    private String status;
    private Priority priority;

    public Task(String title, String description, LocalDate completionDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;
        this.priority = priority;

        this.statusObj = Status.NEW;
        this.status = statusObj.toString();
        this.createdDate = LocalDate.now();
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
}
