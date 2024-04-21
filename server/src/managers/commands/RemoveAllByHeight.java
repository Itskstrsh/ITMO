package managers.commands;

import data.Person;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.TreeSet;

public class RemoveAllByHeight extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;



    public RemoveAllByHeight() {
        super("remove_all_by_height", true);

    }


    @Override
    public Response execute(Request request) {
        String[] args = (String[]) request.getArgs();
        return PersonManager.removeABH(args);
    }
}
