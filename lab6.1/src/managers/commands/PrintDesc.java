package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.Iterator;
import java.util.TreeSet;

public class PrintDesc implements Command{
    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        if(set.isEmpty()){
            System.out.println("Collection is empty");
        }else{
            System.out.println("Collection elements in reverse order: ");
            for (Iterator<Person> it = set.descendingIterator(); it.hasNext(); ) {
                Person person = it.next();
                System.out.println(person.getName());
            }
        }
    }
}
