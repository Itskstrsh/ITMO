package commandManager.commands;

import commandManager.PersonManager;
import interfaces.Command;
/**
 * The Clear class represents a command to clear the collection.
 */
public class Clear implements Command {
    @Override
    public void execute(String[] args) {
        PersonManager.getInstance().getCollection().clear();
        System.out.println("Коллекция очищена");
    }
}
