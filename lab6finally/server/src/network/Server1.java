package network;

import managers.FileManager;
import managers.RunManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Server1 {
    ServerSocketChannel ss;
    private int port;
    private static final Logger serverLogger = Logger.getLogger("logger");
    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(bf));
    FileManager filemanager;
    public RunManager runManager;

    public Server1(RunManager runManager, int port) {
        this.port = port;
        this.runManager = runManager;
    }

    public void run(String path) {
        try {
            openServerSocket();
            serverLogger.info("Connection with client has been created");
            while (true) {
                if (reader.ready()) {
                    String line = reader.readLine();
                    if (line.equals("save")) {
                        String[] args = {path};
                        FileManager.saveCollection(args[1]);
                        serverLogger.info("Collection saved successfully");
                    }
                    if (line.equals("exit")) {
                        String[] args = {path};
                        FileManager.saveCollection(args[1]);
                        serverLogger.info("Collection saves successfully");
                        System.exit(0);
                    }
                }
                SocketChannel clientSocket = ss.accept();
                if (clientSocket != null) {
                    processClientRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openServerSocket() {
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(port));
            ss.configureBlocking(false);
        } catch (IOException e) {
            serverLogger.warning("Error when trying to use port");
        }
    }

    private void processClientRequest(SocketChannel clientSocket) {
        Request userRequest;
        Response responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.socket().getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())) {

            userRequest = (Request) clientReader.readObject();
            serverLogger.info("Received request with command " + userRequest.getArgs()[0]);
            responseToUser = runManager.run(userRequest);
            clientWriter.writeObject(responseToUser);
            serverLogger.info("Answer sended " + responseToUser.getResult());
            clientWriter.flush();
        } catch (ClassNotFoundException | InvalidClassException | NotSerializableException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            serverLogger.warning("I/O error");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                serverLogger.warning("Error while closing the client socket");
            }
        }
    }
}
