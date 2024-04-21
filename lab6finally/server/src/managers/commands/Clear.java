package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;


    public Clear() {
        super("clear", false);
    }


    @Override
    public Response execute(Request request) {
        return PersonManager.clear();
    }
}
