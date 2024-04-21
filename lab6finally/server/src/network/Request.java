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

    public Request(Person person) {
        this.person = person;
    }

    public Request(Person person, String[] args) {
        this.person = person;
        this.args = args;
    }

    public Request(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public Person getPerson() {
        return this.person;
    }
}
