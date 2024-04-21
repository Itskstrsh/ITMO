package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serializable;
import java.util.TreeSet;

public class RemoveLower extends Command implements Serializable {
    private static final long serialVersionUID = 12L;

    public RemoveLower() {
        super("remove_lower", true);

    }

    @Override
    public Response execute(Request request) {
        return PersonManager.removeLower(request.getPerson());
    }
}
