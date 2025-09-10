package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class UpdateId extends Command implements Serializable {
    private static final long serialVersionUID = 14L;

    public UpdateId() {
        super("update_by_id", true);

    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length < 2) {
            return new Response("Not enough info for update organization, maybe you forgot login or password");
        }
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        Person[] personstr = PersonManager.getCollection().toArray(new Person[0]);
        String[] args = request.getArgs();
        try {
            int id = Integer.parseInt(args[1]);
            dataBaseManager.updateObjects(id, user, request.getPerson());
            List list = Arrays.stream(personstr).filter(person -> person.getId() == id).map(person -> request.getPerson()).toList();
            if (list.isEmpty()) {
                return new Response("No element with this Id");
            } else {
                PersonManager.setCollection(dataBaseManager.loadCollection());
                return new Response("Person with id: " + " updated");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect Id");
        }
    }


}