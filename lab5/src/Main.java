import commandManager.CommandInvoker;
import commandManager.PersonManager;
import entity.Person;
import interfaces.Command;

import java.util.TreeSet;

/**
 * The Main class contains the main method to start the program.
 */
public class Main {
    /**
     * The main method creates a CommandInvoker object and invokes the inputCommands method to start the program.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        CommandInvoker invoker = new CommandInvoker();
        String[] infoCommandTokens = {"help"};
        String[] loadCommandTokens = {"load"};
        Command infoCommand = invoker.commands.get("help");
        Command loadCommand = invoker.commands.get("load");
        //infoCommand.execute(infoCommandTokens);
        loadCommand.execute(loadCommandTokens);
        invoker.inputCommands();
        TreeSet<Person> pm = PersonManager.getInstance().getCollection();
      //  pm.add(new Object()); как преобразовать
    }
}
