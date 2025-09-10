package managers.commands;

import interfaces.Executor;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Command implements Executor, Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private final String name;

    private final boolean hasArg;

    public Command(String name,boolean hasArg){
        this.name=name;
        this.hasArg=hasArg;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Command command = (Command) object;
        return Objects.equals(name, command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public Response execute(Request request){return new Response(null);}

    public boolean isHasArg() {
        return hasArg;
    }

    public String getName() {
        return name;
    }
}
