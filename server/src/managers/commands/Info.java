package managers.commands;

import data.Coordinates;
import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;



    public Info() {
        super("info", false);

    }
    @Override
    public Response execute(Request request) {
        return PersonManager.getInfo();
    }
}
