package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * The RemoveAllByHeight class represents a command to remove all elements from the collection with the specified height.
 */
public class RemoveAllByHeight implements Command {

    /**
     * Executes the remove all by height command.
     *
     * @param args the command arguments
     */
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        Scanner scanner = new Scanner(System.in);
        try {
            Double height = Double.parseDouble(args[1]);
            boolean containsPersonWithHeight = collection.stream().anyMatch(person -> Objects.equals(person.getHeight(), height));
            if (containsPersonWithHeight) {
                collection.removeIf(person -> Objects.equals(person.getHeight(), height));
                System.out.println("Объект с ростом " + height + " - удален");
            } else {
                System.out.println("Нет объекта с ростом: " + height);
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный рост!");
        }
    }
}
