package managers.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Location;
import data.Person;
import data.generators.IdGenerator;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeSet;

public class LoadCollection extends Command implements Serializable {
    private static final long serialVersionUID=34543L;


    public LoadCollection(){
        super("load", true);

    }

    @Override
    public Response execute(Request request) {
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
            return new Response("Collection loaded.");
        } catch (FileNotFoundException e) {
            return new Response("No such file");
        } catch (Exception e) {
            return new Response("Error while loading collection: " + e.getMessage());
        }
    }
}
