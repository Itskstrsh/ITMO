package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.Arrays;
import java.util.TreeSet;

public class Show implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        if (set.isEmpty()) {
            System.out.println("Collection is empty");
        } else {
            Person[] pers = set.toArray(new Person[0]);
            System.out.println("The collection contains the following persons: ");
            for (Person person : pers) {
                if (person != null)
                    System.out.println(person);
                else
                    System.out.println("Something wrong...");
            }
        }
    }
}
