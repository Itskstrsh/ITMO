package managers.commands;

import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;



    public Help() {
        super("help", false);

    }


    @Override
    public Response execute(Request request) {
        return PersonManager.help();
    }
}
