package managers.commands;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class RemoveLower extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

    public RemoveLower() {
        super("remove_lower", true);

    }

    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> personTreeSet = PersonManager.getCollection();
        Person person = request.getPerson();
        for (Person pers: personTreeSet){
            if(pers.getHeight()<person.getHeight()){
                dataBaseManager.deleteObject(pers.getId(),user);
                PersonManager.removeById(pers.getId());
            }
        }
        return new Response("Collection changed");
    }
}
