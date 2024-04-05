package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;

import java.util.Iterator;
import java.util.TreeSet;

public class UpdateId implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        try {
            int idToUpdate = Integer.parseInt(args[1]);
            boolean containsPersonWithId = set.stream().anyMatch(person -> person.getId() == idToUpdate);
            if (containsPersonWithId) {
                Person newPerson = PersonGenerator.createPerson(IdGenerator.generateId());
                Iterator<Person> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    if (person.getId() == idToUpdate) {
                        iterator.remove();
                        set.add(newPerson);
                        break;
                    }
                }
                System.out.println("Person with ID: " + idToUpdate + " is updated");
            } else {
                System.out.println("No person with such Id");
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect Id");
        }
    }
}