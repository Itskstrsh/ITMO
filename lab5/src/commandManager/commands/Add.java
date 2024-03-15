package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;
import maker.PersonMaker;

import java.util.Scanner;

/**
 * The Add class represents a command to add a person to the collection.
 */
public class Add implements Command {

    /**
     * Executes the add command.
     *
     * @param args the command arguments (not used in this implementation)
     */
    @Override
    public void execute(String[] args) {
        PersonManager personManager = PersonManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        Person person = PersonMaker.make(scanner);
        personManager.addElement(person);
        System.out.printf("Персонаж %s добавлен в коллекцию. Размер коллекции: %d%n", person.getName(), personManager.getCollection().size());
    }
}
