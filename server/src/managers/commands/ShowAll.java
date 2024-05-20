package managers.commands;

import data.Person;
import managers.DataBaseManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ShowAll extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 94385L;

    public ShowAll() {
        super("show_all", false);
    }

    public Response execute(Request request) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> personTreeSet = dataBaseManager.loadCollection();
        if (personTreeSet.isEmpty()) {
            return new Response("Collection is empty");
        }
        Person[] pers =personTreeSet.toArray(new Person[0]);
        Arrays.sort(pers);
        List<Person>list = Arrays.stream(pers).sorted().toList();
        return new Response("Values of all users: " + list);
    }
}
