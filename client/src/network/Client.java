package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client {

    private String host;
    private int port;
    private int reconnectionTimeout;
    private int maxReconnectionAttempts;

    private Socket socket;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;

    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;

    }

    public Response sendRequest(Request request) throws InterruptedException {
        this.connect();
        for (int reconnectionAttempts = 0; reconnectionAttempts < maxReconnectionAttempts; reconnectionAttempts++) {
            try {
                if (Objects.isNull(serverWriter) || Objects.isNull(serverReader)) throw new IOException();
                serverWriter.writeObject(request);
                serverWriter.flush();
                Response response = (Response) serverReader.readObject();
                this.disconnect();
                return response;
            } catch (IOException e) {
                if (reconnectionAttempts >= maxReconnectionAttempts) {
                    break;
                }
                System.err.println("Another attempt is in  " + reconnectionTimeout / 1000 + " seconds");
                Thread.sleep(reconnectionTimeout);
                this.connect();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            serverWriter = new ObjectOutputStream(socket.getOutputStream());
            serverReader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Connecting to server error");

        }
    }

    public void disconnect() {
        try {
            socket.close();
            serverReader.close();
            serverWriter.close();
        } catch (IOException e) {
            System.err.println("Disconnected");

        }
    }

}