package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serializable;
import java.util.TreeSet;

public class RemoveById extends Command implements Serializable {
    private final static long serialVersionUID = 11L;



    public RemoveById() {
        super("remove_by_id", true);

    }



    @Override
    public Response execute(Request request) {
        return PersonManager.removeById(Integer.valueOf(request.toString()));
    }
}
