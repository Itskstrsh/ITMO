package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.Iterator;
import java.util.TreeSet;
/**
 * The PrintDesc class represents a command to print the elements of the collection in descending order.
 */
public class PrintDesc implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            System.out.println("Элементы кокции в обраном порядке: ");
            for (Iterator<Person> it = collection.descendingIterator(); it.hasNext(); ) {
                Person person = it.next();
                System.out.println(person.getName());
            }

        }
    }
}
