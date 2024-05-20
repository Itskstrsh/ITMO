package managers.commands;

import data.Person;
import managers.CommandManager;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;


    public Clear() {
        super("clear", false);
    }


    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        for (Person person : personTreeSet) {
            if (dataBaseManager.deleteObject(person.getId(), user)) {
                PersonManager.removeById(person.getId());
            }
        }
        return new Response("Collection was cleared");
    }
}
