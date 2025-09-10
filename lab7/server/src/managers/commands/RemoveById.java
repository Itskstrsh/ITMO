package managers.commands;

import data.Person;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serializable;
import java.util.TreeSet;

public class RemoveById extends Command implements Serializable {
    private final static long serialVersionUID = 11L;


    public RemoveById() {
        super("remove_by_id", true);
    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length < 2) {
            return new Response("Not enough info for remove organization, maybe you forgot login or password");
        }
        User user = request.getUser();
        String[] args = request.getArgs();
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        try {
            int id = Integer.parseInt(args[1]);
            if (!dataBaseManager.deleteObject(id, user)) {
                return new Response("Can't delete person");
            } else {
                personTreeSet.removeIf(person -> person.getId() == id);
                PersonManager.setCollection(personTreeSet);
                return new Response("Element with id: " + id + " deleted");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect Id value");
        }
    }
}
