import managers.*;
import network.Server1;

import java.nio.channels.NotYetBoundException;


public class Server {
    public static void main(String[] args) throws NotYetBoundException {
        CommandManager commandManager = new CommandManager();
        PersonManager personManager = new PersonManager();
        RunManager runManager = new RunManager(commandManager);
        DataBaseManager dataBaseManager = new DataBaseManager();
        PersonManager.setCollection(dataBaseManager.loadCollection());
        Server1 server1 = new Server1(runManager, 5000, dataBaseManager, personManager);
        server1.run();
    }
}

