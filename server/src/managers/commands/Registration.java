package managers.commands;

import exceptions.UserExistsException;
import managers.CommandManager;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;

public class Registration extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 210012L;

    public Registration() {
        super("registration", true);
    }

    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        try {
            dataBaseManager.addUser(user);
            return new Response("User added successfully");
        } catch (UserExistsException e) {
            return new Response(e.getMessage());
        } catch (Exception e) {
            return new Response("Error while adding user, try another login");
        }
    }
}
