package managers;

import managers.commands.*;
import network.Request;
import network.Response;

import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static Map<String, Command> commands;

    public CommandManager() {
        commands = new HashMap<>();
        commands.put("add", new Add());
        commands.put("help", new Help());
        commands.put("print_descending", new PrintDesc());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("remove_all_by_height", new RemoveAllByHeight());
        commands.put("update", new UpdateId());
        commands.put("info", new Info());
        commands.put("remove_by_id", new RemoveById());
        commands.put("add_if_max", new AddIfMax());
        commands.put("remove_lower", new RemoveLower());
        commands.put("execute_script", new ExecuteScript(commands));
        commands.put("filter_greater_than_nationality", new FilterGTN());
        commands.put("registration", new Registration());
        commands.put("authorization", new Authorisation());
        commands.put("get_user", new GetUser());
        commands.put("exit_from_account", new ExitFromAcc());
        commands.put("show_all",new ShowAll());
    }

    public static Response execute(Request request) throws Exception {
        Command command = commands.get(request.getArgs()[0]);
        if (command == null) {
            return new Response("No such command");
        } else {
            if (command.isHasArg() && request.getArgs().length < 2) {
                return new Response("No arguments for command: " + command.getName());
            }
            return command.execute(request);
        }
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

}





