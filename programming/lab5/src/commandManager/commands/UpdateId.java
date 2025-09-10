package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;
import maker.PersonMaker;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * The UpdateId class represents a command to update an element in the collection by its id.
 */
public class UpdateId implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        Scanner scanner = new Scanner(System.in);
        try {
            int idToUpdate = Integer.parseInt(args[1]);
            boolean containsPersonWithId = collection.stream().anyMatch(person -> person.getId() == idToUpdate);
            if (containsPersonWithId) {
                Person newPerson = PersonMaker.make(scanner);
                Iterator<Person> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    if (person.getId() == idToUpdate) {
                        iterator.remove();
                        collection.add(newPerson);
                        break;
                    }
                }
                System.out.println("Обновлен персонаж с ID: " + idToUpdate);
            } else {
                System.out.println("Персонажа с этим таким ID нету");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный id");
        }

    }
}
