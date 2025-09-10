package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeSet;

public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;


    public Show(){
        super("show",false);

    }
    @Override
    public Response execute(Request request) {
        return PersonManager.show();
    }
}
