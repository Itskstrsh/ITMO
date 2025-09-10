package managers.commands;

import data.Person;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

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
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        float height = Float.parseFloat(request.getArgs()[1]);
        for (Person pers : personTreeSet) {
            if (pers.getHeight() == height) {
                dataBaseManager.deleteObject(pers.getId(), user);
                PersonManager.removeById(pers.getId());
            }
        }
        return new Response("Collection changed");
    }
}
