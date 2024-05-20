package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

public class PrintDesc extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 9L;


    public PrintDesc() {
        super("print_descending", false);

    }
    @Override
    public Response execute(Request request) {
        return PersonManager.printDesc();
    }
}
