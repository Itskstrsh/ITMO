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
    public static User user = new User(null, null);

    public void execute() throws InterruptedException {
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client(host, port, reconnectionTimeout, maxReconnectionTimeout);
        System.out.println("Input registration [login] [password] for registration\n" +
                "'help' for information about commands");
        while (true) {
            String cmd = (scanner.nextLine() + " ").trim();
            input = cmd.split(" ");
            if (input[0].equals("registration")) {
                if (input.length >= 3) {
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input, user)).getResult());
                    user = new User(null, null);
                } else {
                    System.out.println("Not enough info for registration");
                }
            }
            else if (input[0].equals("exit_from_account")) {
                user = new User(null, null);
                System.out.println("Exit from account confirmed");
            }
            else if(input[0].equals("get_login")){
                System.out.println(user.getLogin());
            }
            else if (input[0].equals("authorization")) {
                if (input.length >= 3) {
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input, user)).getResult());
                } else {
                    System.out.println("Not enough info for authorization");
                }
            }
            else if ((input[0].equals("add") || input[0].equals("add_if_max") || (input[0].equals("remove_lower"))) && user.getPassword()!= null && user.getLogin()!=null) {
                Person person = PersonGenerator.createPerson(IdGenerator.generateId());
                System.out.println(client.sendRequest(new Request(person, input, user)).getResult());
            }
            else if (input[0].equals("exit")) {
                System.out.println("Bye bye");
                System.exit(1);
            }
            else if (user.getLogin() != null && user.getPassword() != null) {
                System.out.println(client.sendRequest(new Request(input, user)).getResult());
            } else {
                System.out.println("Authorization is required to enter commands");
            }
        }
    }
}
