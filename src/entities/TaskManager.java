package entities;

import java.util.List;

public final class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;

    private TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }


    public static TaskManager getInstance(List<Task> tasks) {
        if (instance == null) {
            instance = new TaskManager(tasks);
        }
        return instance;
    }


}
