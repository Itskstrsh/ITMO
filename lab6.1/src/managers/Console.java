package managers;

import managers.commands.LoadCollection;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
    public String path;

    public void start(InputStream input){
        Scanner scanner = new Scanner(input);
        CommandManager commandManager = new CommandManager();
        new PersonManager();
        commandManager.registerCommand("load", new LoadCollection());
        try {
            commandManager.startExecuting("load");
        } catch (Exception e) {
            System.out.println("Error while loading collection: " + e.getMessage());
        }
        while(scanner.hasNextLine()){
            String command = scanner.nextLine().trim();
            if(!command.isEmpty()){
                try{
                    commandManager.startExecuting(command);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
