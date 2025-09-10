package interfaces;
/**
 * The Command interface represents a command that can be executed with arguments.
 */
public interface Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args the arguments passed to the command
     */
    void execute(String[] args);
}


