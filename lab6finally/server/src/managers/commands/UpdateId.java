package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

public class UpdateId extends Command implements Serializable {
    private static final long serialVersionUID=14L;

    public UpdateId(){
        super("update_by_id", true);

    }

    @Override
    public Response execute(Request request) {
        return PersonManager.updateId(request.getArgs());
    }


}