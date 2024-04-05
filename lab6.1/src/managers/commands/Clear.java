package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.TreeSet;

public class Clear implements Command{
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        set.clear();
        PersonManager.setCollection(set);
        System.out.println("Сollection cleared");
    }
}
