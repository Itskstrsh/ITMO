package commandManager;

import java.util.*;

import commandManager.commands.*;
import interfaces.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The CommandInvoker class manages the execution of commands and maintains command history.
 */
public class CommandInvoker {
    public Map<String, Command> commands;
    private String[] commandHistory;
    private int commandHistoryIndex;
    /**
     * Constructs a new CommandInvoker object and initializes the commands map.
     */
    public CommandInvoker() {
        commands = new HashMap<String, Command>();
        commandHistory = new String[15];
        commandHistoryIndex = 0;
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
        commands.put("history", new History(commandHistory));
        commands.put("save", new Save());
        commands.put("execute_script", new ExecuteScript(commands));
        commands.put("filter_greater_than_nationality", new FilterGTN());
        commands.put("load",new LoadCollection());
    }

    /**
     * Prompts the user for input commands and executes them.
     */
    public void inputCommands() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                Command command = commands.get(tokens[0]);
                command.execute(tokens);
                if (commandHistoryIndex < commandHistory.length) {
                    commandHistory[commandHistoryIndex] = line;
                    commandHistoryIndex++;
                } else {
                    System.arraycopy(commandHistory, 1, commandHistory, 0, commandHistory.length - 1);
                    commandHistory[commandHistory.length - 1] = line;
                }
            } catch (NullPointerException e) {
                System.out.println("The command does not exist!");
            }
        }
    }
}

