package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * The FilterGTN class represents a command to filter persons by nationality.
 */
public class FilterGTN implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        Scanner scanner = new Scanner(System.in);
        try {
            String nationalityValue = args[1];
            TreeSet<Person> filteredPersons = PersonManager.filterPersonsByNationality(nationalityValue);
            System.out.println("Персонажи, значение поля nationality которых больше заданного: ");
            for (Person person : filteredPersons) {
                System.out.println(person.getName());
            }
        } catch (Exception e) {
            System.out.println("Введите значение типа String");
        }

    }
}
