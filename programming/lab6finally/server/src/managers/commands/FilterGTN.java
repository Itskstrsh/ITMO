package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class FilterGTN extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;


    public FilterGTN() {
        super("filterGTN", true);

    }

    @Override
    public Response execute(Request request) {
        return PersonManager.filterGTN();
    }

}
