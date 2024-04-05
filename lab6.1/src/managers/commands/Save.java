package managers.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Person;
import managers.PersonManager;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeSet;

public class Save implements Command {
    private String filePath = "collection.json";

    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getCollection();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buffer = gson.toJson(collection).getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
            System.out.println("Collection data is saved!");
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
