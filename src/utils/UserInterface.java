package utils;

import entities.Task;
import entities.TaskManager;
import enums.Priority;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner sc = new Scanner(System.in);

    private TaskManager taskManager;
    private List<Task> tasks = new ArrayList<>();

    public UserInterface() {
        List<Task> tasksTemp = new ArrayList<>();
        try {
            tasksTemp = FileUtil.readFromFile();
        } catch (NoSuchFileException noSuchFileException) {
            System.err.println("Файл не создан.");
            tasksTemp = fillTasks();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
        tasks.addAll(tasksTemp);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> sortByPriority() {
        return tasks.stream().sorted(Comparator.comparing(Task::getPriority)).toList();
    }


    public void showwsss() {
        tasks.forEach(
                task -> {
                    boolean isBefore = task.getCompletionDate().isBefore(LocalDate.now());

                    System.out.print(
                            (isBefore ? "* " : "")
                    );
                    System.out.print(task.getTitle());
                    System.out.println();
                }
        );
    }


    private List<Task> fillTasks() {
        List<Task> temp = new ArrayList<>();
        try {
            temp.addAll(FileUtil.readFromFile());
        } catch (NoSuchFileException noSuchFileException) {
            System.out.println("Файл не создан.");

            System.out.println("Сколько задач вы хотите создать?");
            int count = enterValidNum(5);

            for (int i = 0; i < count; i++) {
                temp.add(createNewTask());
            }


        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }

        FileUtil.writeToFile(temp);
        return temp;
    }

    private Task createNewTask() {
        String title = enterValidString("название задачи");
        String description = enterValidString("описание задачи");
        Priority priority = selectPriority();
        LocalDate completionDate = enterDate();

        return new Task.TaskBuilder()
                .title(title)
                .description(description)
                .priority(priority)
                .completionDate(completionDate)
                .build();
    }


    private int enterValidNum(int bound) {
        int count;
        try {
            count = Integer.parseInt(sc.nextLine().trim());
            if (count > bound || count < 0)
                throw new IndexOutOfBoundsException("Вы вышли за пределы");
        } catch (NumberFormatException e) {
            System.out.println("Вы ошиблись введите корректно");
            return enterValidNum(bound);
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            System.err.println(outOfBoundsException.getMessage());
            return enterValidNum(bound);
        }
        return count;
    }

    private String enterValidString(String title) {
        System.out.println("Введите " + title);
        String valid;
        try {
            valid = sc.nextLine().trim();

            if (valid.isEmpty() || valid.isBlank()) {
                throw new NullPointerException();
            }

        } catch (NullPointerException e) {
            System.err.println("Вы ввели пустую строку! Повторите ввод");
            return enterValidString(title);
        }

        return valid;
    }

    private Priority selectPriority() {
        Priority[] priorities = Priority.values();

        System.out.println("Выберите номер приоритета");
        for (int i = 0; i < priorities.length; i++) {
            System.out.println((i + 1) + " | " + priorities[i]);
        }

        int idx = enterValidNum(priorities.length);

        return priorities[idx - 1];


    }


    private LocalDate enterDate() {
        LocalDate ld;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String enter = enterValidString(("Введите дату в формате ДД.ММ.ГГГГ"));
        try {
            ld = LocalDate.parse(enter, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Вы вввели дату в неверном формате. ");
            return enterDate();
        }
        return ld;
    }


    public void showTasks() {
        tasks.forEach(System.out::println);
    }
}
