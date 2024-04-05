package managers.commands;

import managers.CommandManager;

public class History implements Command {
    @Override
    public void execute(String[] args) {
        String[] sp = new String[15];
        int n = 0;
        for (Command command : CommandManager.lastCommands) {
            n += 1;
        }
        for (int i = 0; i < 15; i++) {
            if (!((sp[i]) == null)) {
                System.out.println("-" + sp[i]);
            }
        }
    }
}
