package commandManager.commands;

import commandManager.CommandInvoker;
import interfaces.Command;

/**
 * The History class represents a command to display the command history.
 */
public class History implements Command {
    private String[] commandHistory;

    /**
     * Constructs a new History object with the specified command history.
     *
     * @param commandHistory the array containing the command history
     */
    public History(String[] commandHistory) {
        this.commandHistory = commandHistory;
    }

    /**
     * Executes the history command to display the command history.
     *
     * @param args the command arguments (not used in this implementation)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команды, введённые ранее: ");
        for (int i = 0; i < commandHistory.length && commandHistory[i] != null; i++) {
            System.out.println((i + 1) + ". " + commandHistory[i]);
        }
    }
}
