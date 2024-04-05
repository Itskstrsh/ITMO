package managers.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Person;
import data.generators.IdGenerator;
import managers.PersonManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeSet;

public class LoadCollection implements Command {
    @Override
    public void execute(String[] args) {
        try {
            // Read JSON data from file using Scanner
            StringBuilder jsonStringBuilder = new StringBuilder();
            File file = new File("collectionforloading.json");
            Scanner scanner = new Scanner(file);
            IdGenerator.generateId();
            while (scanner.hasNextLine()) {
                jsonStringBuilder.append(scanner.nextLine());
            }
            // Convert JSON data to TreeSet<Person> using Gson
            Type setType = new TypeToken<TreeSet<Person>>() {
            }.getType();
            TreeSet<Person> collection = new Gson().fromJson(jsonStringBuilder.toString(), setType);
            // Update PersonManager collection
            PersonManager.setCollection(collection);
            System.out.println("Collection loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        } catch (Exception e) {
            System.out.println("Error while loading collection: " + e.getMessage());
        }
    }
}
