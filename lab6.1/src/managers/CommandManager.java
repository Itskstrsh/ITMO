package managers;

import managers.commands.*;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static Map<String, Command> commands;
    public static ArrayDeque<Command> lastCommands = new ArrayDeque<>();
    public CommandManager() {
        commands = new HashMap<>();
        commands.put("add", new Add());
        commands.put("exit", new Exit());
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
        commands.put("history", new History());
        commands.put("save", new Save());
        commands.put("execute_script", new ExecuteScript(commands));
        commands.put("filter_greater_than_nationality", new FilterGTN());
        commands.put("load",new LoadCollection());
    }
    public static void startExecuting(String line){
        String commandName = line.split(" ")[0];
        if(!commands.containsKey(commandName)){
            System.out.println("No such command");
        }
        Command command = commands.get(commandName);
        command.execute(line.split(" "));
        if(!(lastCommands==null)&&lastCommands.size()==15){
            lastCommands.pop();
            lastCommands.addLast(command);
        }else{
            assert lastCommands != null;
            lastCommands.addFirst(command);
        }
    }
    public Map<String, Command> getCommands(){
        return commands;
    }
    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }
}
