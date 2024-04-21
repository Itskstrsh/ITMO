package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class AddIfMax extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    public AddIfMax() {
        super("add_if_max", true);
    }

    @Override
    public Response execute(Request request) throws Exception {
        return PersonManager.addIfMax(request.getPerson());
    }
}
