import managers.CommandManager;
import managers.FileManager;
import managers.PersonManager;
import managers.RunManager;
import managers.commands.Help;
import network.Server1;

import java.io.IOException;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        final Logger serverLogger = Logger.getLogger("logger");
        PersonManager personManager = new PersonManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager(personManager);
        RunManager runManager = new RunManager(commandManager);
        try {
            fileManager.readFromCollection("collection.json");
            serverLogger.info("File loaded successfully");
        } catch (IOException e) {
            serverLogger.info("Error with file reading: " + e.getMessage());
        }
        Server1 server = new Server1(runManager, 5000);
        server.run(args[0]);
    }
}