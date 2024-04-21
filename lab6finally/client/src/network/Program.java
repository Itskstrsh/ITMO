package network;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import exceptions.*;

import java.util.Scanner;

public class Program {
    private static final String host = "localhost";
    private static final int port = 5000;
    private static final int reconnectionTimeout = 5000;
    private static final int maxReconnectionTimeout = 5;

    public void execute() throws InterruptedException {
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client(host, port, reconnectionTimeout, maxReconnectionTimeout);
        System.out.println("Input help for information about commands");
        while (true) {
            String cmd = (scanner.nextLine() + " ").trim();
            input = cmd.split(" ");
            if (input[0].equals("add") || input[0].equals("add_if_min")) {
                Person person = PersonGenerator.createPerson(IdGenerator.generateId());
                System.out.println(client.sendRequest(new Request(person, input)).getResult());
            } else if (input[0].equals("exit")) {
                System.out.println("Bye bye");
                System.exit(1);
            } else {
                System.out.println(client.sendRequest(new Request(input)).getResult());
            }
        }
    }
}
