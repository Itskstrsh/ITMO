package interfaces;

import network.Request;
import network.Response;

public interface Executor {
    Response execute(Request request) throws Exception;
}
