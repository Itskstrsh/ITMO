package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;

import java.util.TreeSet;

public class AddIfMax implements Command {
    @Override
    public void execute(String[] args) {
        Person person = PersonGenerator.createPerson(IdGenerator.generateId());
        TreeSet<Person> set = PersonManager.getCollection();
        if (set.last().compareTo(person) == -1) {
            set.add(person);
            System.out.println("Person added successfully");
        } else{
            System.out.println("The value does not exceed the value of the largest element. Person not added");
        }

    }
}
