package commandManager.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
/**
 * The Save class represents a command to save the collection to a file in JSON format.
 */
public class Save implements Command {

    private String filePath = "collection.json";
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buffer = gson.toJson(collection).getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
            System.out.println("Данные коллекцции успешно сохранены!");
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
}
