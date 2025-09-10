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

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    public Add() {
        super("add", false);
    }


    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DataBaseManager dataBaseManager = new DataBaseManager();
        Person person = request.getPerson();
        if (dataBaseManager.addPerson(person, user) != -1) {
            PersonManager.setCollection(dataBaseManager.loadCollection());
            return new Response("Person added");
        }
        return new Response("Add failed");
    }
}
