package network;

import managers.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class Server1 {
    ServerSocketChannel ss;
    private Request request;
    private Response response;
    private int port;
    public static final Logger serverLogger = Logger.getLogger("logger");
    public RunManager runManager;
    private DataBaseManager dataBaseManager;
    private PersonManager personManager;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    private ForkJoinPool forkJoinPool = new ForkJoinPool(5);

    public Server1(RunManager runManager, int port, DataBaseManager dataBaseManager, PersonManager personManager) {
        this.port = port;
        this.runManager = runManager;
        this.dataBaseManager = dataBaseManager;
        this.personManager = personManager;
    }

    public void run() {
        try {
            openServerSocket();
            serverLogger.info("Connection with client has been created");
            while (true) {
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
            serverLogger.info("Server socket bound to port " + port);
        } catch (IOException e) {
            serverLogger.warning("Error when trying to use port");
        }
    }

    private void processClientRequest(SocketChannel socketChannel) {
        try (
                ObjectInputStream reader = new ObjectInputStream(socketChannel.socket().getInputStream());
                ObjectOutputStream writer = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        ) {
            fixedThreadPool.submit(() -> readRequest(reader)).get();
            serverLogger.info("Received request with command " + request.getArgs()[0]);
            fixedThreadPool.submit(() -> commandExecute(request)).get();
            forkJoinPool.submit(() -> sendResponse(response, writer)).get();
            serverLogger.info("Answer sent " + response.getResult());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            serverLogger.warning("I/O error");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                serverLogger.warning("Error closing the client socket");
            }
        }
    }

    private synchronized void readRequest(ObjectInputStream objectInputStream) {
        try {
            request = (Request) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void sendResponse(Response response, ObjectOutputStream writer) {
        try {
            writer.writeObject(response);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response commandExecute(Request request) throws Exception {
        response = CommandManager.execute(request);
        return response;
    }
}
