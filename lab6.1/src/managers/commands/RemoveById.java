package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.TreeSet;

public class RemoveById implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        System.out.println("Enter the Id by which you want to delete the element: ");
        int idToRemove = Integer.parseInt(args[1]);
        boolean removed = set.removeIf(person -> person.getId() == idToRemove);
        if (removed) {
            System.out.println("Person with Id " + idToRemove + " is removed");
        } else {
            System.out.println("No Person with such Id");
        }
    }
}
