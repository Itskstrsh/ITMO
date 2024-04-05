package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.TreeSet;

public class FilterGTN implements Command{
    @Override
    public void execute(String[] args) {
        try {
            String nationalityValue = args[1];
            TreeSet<Person> filteredPersons = PersonManager.filterPersonsByNationality(nationalityValue);
            System.out.println("Persons whose nationality field value is greater than the specified one: ");
            for (Person person : filteredPersons) {
                System.out.println(person.getName());
            }
        } catch (Exception e) {
            System.out.println("Input a String value");
        }
    }
}
