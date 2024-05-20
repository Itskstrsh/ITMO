package managers.commands;

import data.Person;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;


    public Show() {
        super("show", false);
    }

    @Override
    public Response execute(Request request) {
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        if (personTreeSet.isEmpty()) {
            return new Response("Collection is empty");
        }
        DataBaseManager dataBaseManager = new DataBaseManager();
        List<String> list = dataBaseManager.showObjects(request.getUser());
        if(list.isEmpty()){
            return new Response("Collection is empty for current user");
        }
        return new Response("All values of user: " + request.getUser().getLogin()+" - " + list);
    }
}
