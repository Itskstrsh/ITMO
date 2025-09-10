package managers.commands;

import managers.CommandManager;
import network.Request;
import network.Response;
import network.User;

public class GetUser extends Command {

    public GetUser() {
        super("get_user", false);
    }

    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        return new Response(user.getLogin());
    }
}
