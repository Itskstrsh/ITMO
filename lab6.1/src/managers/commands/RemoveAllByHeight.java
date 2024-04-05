package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.Objects;
import java.util.TreeSet;

public class RemoveAllByHeight implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        try {
            Double height = Double.parseDouble(args[1]);
            boolean containsPersonWithHeight = set.stream().anyMatch(person -> Objects.equals(person.getHeight(), height));
            if (containsPersonWithHeight) {
                set.removeIf(person -> Objects.equals(person.getHeight(), height));
                System.out.println("Person with height: " + height + " -     is deleted");
            } else {
                System.out.println("No person with such height: " + height);
            }
        }catch (NumberFormatException e){
            System.out.println("Input integer value");
        }
    }
}
