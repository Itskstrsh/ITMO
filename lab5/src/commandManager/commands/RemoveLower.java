package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;
import maker.PersonMaker;

import java.util.Scanner;
import java.util.TreeSet;
/**
 * The RemoveLower class represents a command to remove all elements from the collection lower than the specified one.
 */
public class RemoveLower implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        System.out.println("Введите нового персонажа: ");
        Scanner scanner = new Scanner(System.in);
        Person newperson = PersonMaker.make(scanner);
        boolean removed = collection.removeIf(person -> person.compareTo(newperson) < 0);
        if (removed) {
            collection.add(newperson);
            System.out.println("Элементы удалены");

        } else {
            System.out.println("В коллекции нет элементов меньших, чем указанный");
        }

    }
}
