package commandManager.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeSet;

import commandManager.IdStorage;
import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

/**
 * The LoadCollection class represents a command to load a collection of Person objects from a JSON file.
 */
public class LoadCollection implements Command {
    /**
     * Executes the load_collection command to load a collection of Person objects from a JSON file.
     *
     * @param args the command arguments (not used in this implementation)
     */
    @Override
    public void execute(String[] args) {
        try {

            // Read JSON data from file using Scanner
            StringBuilder jsonStringBuilder = new StringBuilder();
            File file = new File("collection1.json");
            Scanner scanner = new Scanner(file);
            IdStorage.setCurrId(1);
            while (scanner.hasNextLine()) {
                jsonStringBuilder.append(scanner.nextLine());
            }
            // Convert JSON data to TreeSet<Person> using Gson
            Type setType = new TypeToken<TreeSet<Person>>() {
            }.getType();
            TreeSet<Person> collection = new Gson().fromJson(jsonStringBuilder.toString(), setType);
            // Update PersonManager collection
            PersonManager.getInstance().setCollection(collection);
//            int maxid = 0;
//            for (Person person : collection) {
//                if (person.getId() > maxid) {
//                    maxid = person.getId();
//                }
//            }
//            IdStorage.setCurrId(maxid);

            System.out.println("Коллекция успешно загружена.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузки коллекции: " + e.getMessage());
        }
    }
}
