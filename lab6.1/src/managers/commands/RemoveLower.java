package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;

import java.util.TreeSet;

public class RemoveLower implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        Person newperson = PersonGenerator.createPerson(IdGenerator.generateId());
        boolean removed = set.removeIf(person -> person.compareTo(newperson) < 0);
        if (removed) {
            set.add(newperson);
            System.out.println("Elements removed");

        } else {
            System.out.println("The collection contains no elements smaller than the specified one");
        }
    }
}
