package network;

import data.Person;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;
    public Person person;
    String[] args;
    public Request(Person person, String[] args) {
        this.person = person;
        this.args = args;
    }

    public Request(String[] args) {
        this.args = args;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
