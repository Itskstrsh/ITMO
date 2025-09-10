package managers;

import network.Request;
import network.Response;

public class RunManager {
    private final CommandManager commandManager;

    public RunManager(CommandManager commandManager){
        this.commandManager=commandManager;
    }
    public Response run(Request request) throws Exception {
        return commandManager.execute(request);
    }
}

