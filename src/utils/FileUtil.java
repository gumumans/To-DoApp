package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private static final Gson GSON = new GsonBuilder()
            .setDateFormat("dd.MM.yyyy")
            .setPrettyPrinting()
//            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();
    private static final Path PATH = Path.of("data/tasks.json");

    private FileUtil() {
    }


    public static List<Task> readFromFile() throws IOException {
        Type taskType = new TypeToken<Map<String, List<Task>>>() {
        }.getType();
        Map<String, List<Task>> map = GSON.fromJson(Files.readString(PATH), taskType);
        return map.get("tasks");
    }

    public static void writeToFile(List<Task> tasks) {
        Map<String, List<Task>> map = new HashMap<>();
        map.put("tasks", tasks);

        try (FileWriter writer = new FileWriter(String.valueOf(PATH))) {
            GSON.toJson(map, writer);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
