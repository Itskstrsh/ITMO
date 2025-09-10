package commandManager.commands;

import interfaces.Command;

/**
 * The Exit class represents a command to exit the program.
 */
public class Exit implements Command {

    /**
     * Executes the exit command.
     *
     * @param args the command arguments (not used in this implementation)
     */
    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
