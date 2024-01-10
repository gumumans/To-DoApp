import entities.Task;
import utils.FileUtil;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            tasks.add(new Task(("Task " + i), "Test", LocalDate.now().plusDays(new Random().nextLong(15)), Priority.MEDIUM  ));
//
//        }

        try {
            tasks.addAll(FileUtil.readFromFile());

        } catch (NoSuchFileException noSuchFileException) {
            System.out.println("Файл не создан.");
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }


        tasks.forEach(
                System.out::println
        );

        System.out.println(tasks);

//        FileUtil.writeToFile(tasks);
    }
}