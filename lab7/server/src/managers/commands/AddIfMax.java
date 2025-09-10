package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.CommandManager;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class AddIfMax extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    public AddIfMax() {
        super("add_if_max", true);
    }

    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DataBaseManager databaseManager = new DataBaseManager();
        Person person = request.getPerson();
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        Person[] pers = personTreeSet.toArray(new Person[0]);
        Arrays.sort(pers, Comparator.comparingInt(Person::getId));
        if (pers.length == 0 || person.getId() > pers[pers.length - 1].getId()) {
            if (databaseManager.addPerson(person,user)==1){
                personTreeSet.add(person);
            }
            return new Response("Person added");
        }
        return new Response("Value of id isn't max");
    }
}
