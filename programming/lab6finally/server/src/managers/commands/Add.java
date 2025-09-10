package managers.commands;

import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;
import network.Request;
import network.Response;

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
        return PersonManager.addPers(request.getPerson());
    }
}
