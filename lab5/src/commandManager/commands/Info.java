package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.Date;
import java.util.TreeSet;
/**
 * The Info class represents a command to display information about the collection.
 */
public class Info implements Command {
    @Override
    public void execute(String[] args) {
        PersonManager pm = PersonManager.getInstance();
        TreeSet<Person> collection = pm.getCollection();
        Date date = pm.getInitializationDate();
        System.out.printf("Тип коллекции: %s\n", collection.getClass());
        System.out.printf("Дата инициализации: %s\n", date == null ? "Коллекция не инициализирована" : date);
        System.out.printf("Количество элементов: %s\n", collection.size());
    }
}
