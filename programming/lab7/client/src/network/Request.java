package network;

import data.Person;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;
    public Person person;
    String[] args;
    public User user;

    public Request(Person person, String[] args, User user) {
        this.person = person;
        this.args = args;
        this.user = user;
    }

    public Request(String[] args, User user) {
        this.args = args;
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public User getUser() {
        return user;
    }

    public String[] getArgs() {
        return args;
    }

}
