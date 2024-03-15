package commandManager.commands;
import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;
import maker.PersonMaker;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * The AddIfMax class represents a command to add a person to the collection if it is the maximum.
 */
public class AddIfMax implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        System.out.println("Введите нового персонажа: ");
        Scanner scanner = new Scanner(System.in);
        Person newperson = PersonMaker.make(scanner);
        if(collection.last().compareTo(newperson)==-1){
            collection.add(newperson);
            System.out.println("Персонаж добавлен к коллекцию");
        }else{
            System.out.println("Значение не превышает значения наибольшего элемента. Персонаж не добавлен!");
        }
    }
}
