package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * The Show class represents a command to display all elements of the collection.
 */
public class Show implements Command {
    public void execute(String[] args) {
        PersonManager pm = PersonManager.getInstance();
        Person[] personArray = pm.getCollection().toArray(new Person[0]);
        if (personArray.length == 0) {
            System.out.println("Коллекция пуста");
        } else {
//            Arrays.sort(personArray, Comparator.comparingInt(Person::getId));
            System.out.println("Коллекция содержит следующих персонажей, отсортированных по id:");
            System.out.println("Размер масива: " + personArray.length);
            for (Person person : personArray) {
                if (person != null)
                    System.out.println(person);
                else
                    System.out.println("Проблемы....");
            }
        }
    }
}

