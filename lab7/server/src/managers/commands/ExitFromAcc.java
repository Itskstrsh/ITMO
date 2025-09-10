package managers.commands;


import network.Request;
import network.Response;

public class ExitFromAcc extends Command{


    public ExitFromAcc() {
        super("exit_from_account", false);
    }

    public Response execute(Request request){
        return new Response("Exit confirmed");
    }
}
