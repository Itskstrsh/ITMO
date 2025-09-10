package network;

import data.Person;
import managers.commands.Command;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;
    public Person person;
    String[] args;
    public User user;


    public Request(Person person, User user) {
        this.person = person;
        this.user = user;
    }

    public Request(Person person, String[] args, User user) {
        this.person = person;
        this.args = args;
        this.user = user;
    }

    public Request(String[] args, User user) {
        this.args = args;
        this.user = user;
    }

    public Request(Person person, String[] args) {
        this.person = person;
        this.args = args;
    }

    public Request(Person person) {
        this.person = person;
    }

    public Request(String[] args) {
        this.args = args;
    }

    public User getUser() {
        return user;
    }

    public String[] getArgs() {
        return args;
    }

    public Person getPerson() {
        return person;
    }


}
