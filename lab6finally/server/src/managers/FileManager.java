package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.Person;
import data.generators.IdGenerator;
import network.Response;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeSet;

public class FileManager {
    private final PersonManager personManager;


    public FileManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public void readFromCollection(String file_path) throws IOException {
        try {
            StringBuilder jsonStringBuilder = new StringBuilder();
            File file = new File("collection.json");
            Scanner scanner = new Scanner(file);
            IdGenerator.generateId();
            while (scanner.hasNextLine()) {
                jsonStringBuilder.append(scanner.nextLine());
            }
            Type setType = new TypeToken<TreeSet<Person>>() {
            }.getType();
            TreeSet<Person> collection = new Gson().fromJson(jsonStringBuilder.toString(), setType);
            PersonManager.setCollection(collection);
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        } catch (Exception e) {
            System.out.println("Problem with loading collection");
        }
    }

    public static void saveCollection(String file_path) throws IOException {
        File file = new File(file_path);
        TreeSet<Person> collection = PersonManager.getCollection();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = gson.toJson(collection).getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
            System.out.println("Collection data is saved!");
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
