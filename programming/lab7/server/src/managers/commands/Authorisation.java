package managers.commands;

import managers.CommandManager;
import managers.DataBaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;

public class Authorisation extends Command implements Serializable {
    @Serial
    private final static long serialVersionUID = 232321L;


    public Authorisation() {
        super("authorization", true);
    }

    public Response execute(Request request) {
        DataBaseManager databaseManager = new DataBaseManager();
        User user = request.getUser();
        if (databaseManager.existUser(user)) {
            return new Response("Successfull authorization with login: " + user.getLogin());
        }
        return new Response("No user with such values");
    }
}
