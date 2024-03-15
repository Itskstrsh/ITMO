package commandManager.commands;

import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.Scanner;
import java.util.TreeSet;
/**
 * The RemoveById class represents a command to remove an element from the collection by its id.
 */
public class RemoveById implements Command {
    @Override
    public void execute(String[] args) {
        TreeSet<Person> collection = PersonManager.getInstance().getCollection();
        System.out.print("Введите Id, по которому хотите удалить элемент: ");
        Scanner scanner = new Scanner(System.in);
        int idToRemove = scanner.nextInt();
        boolean removed = collection.removeIf(person -> person.getId() == idToRemove);
        if(removed){
            System.out.println("Элемент с Id "+ idToRemove+" удален");
        }else{
            System.out.println("Элемента с таким Id не существует");
        }
    }
}
