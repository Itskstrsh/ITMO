package managers.commands;

import data.*;
import managers.CommandManager;
import managers.DataBaseManager;
import managers.PersonManager;
import network.Request;
import network.Response;
import network.Server1;
import network.User;


import java.io.*;
import java.util.*;


public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;


    Map<String, Command> commands;

    public ExecuteScript(Map<String, Command> commands) {
        super("execute_script", true);
        this.commands = commands;

    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != 2) {
            return new Response("You need to write file name");
        }
        String path = request.getArgs()[1];
        return executeScript(path);
    }

    private Response executeScript(String filePath) {
        StringBuilder result = new StringBuilder();
        File file = new File(filePath);
        try {
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
            BufferedReader r = new BufferedReader(new InputStreamReader(bf));
            while (true) {
                try {
                    String line = r.readLine().trim();
                    Server1.serverLogger.info(line);
                    if (!line.isEmpty()) {
                        String[] tokens = line.split(" ");
                        Command command = commands.get(tokens[0]);
                        if (command != null) {
                            if (tokens[0].equals("execute_script") && tokens.length > 1) {
                                String nestedScriptPath = tokens[1];
                                if (Objects.equals(nestedScriptPath, filePath)) {
                                    result.append("Recursion!" + '\n');
                                }
                                executeScript(nestedScriptPath);
                            } else if (tokens[0].equals("add")) {
                                addPerson(tokens);
                                result.append("Person added" + '\n');
                            } else {
                                String[] data = {tokens[0]};
                                Request request = new Request(data);
                                result.append(command.execute(request).getResult()).append('\n');
                            }
                        } else {
                            System.out.println("No such command: " + tokens[0]);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            return new Response(result.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return new Response(result.toString());
    }


    private void addPerson(String[] tokens) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        TreeSet<Person> set = PersonManager.getCollection();
        // Check if there are enough arguments to create a person
        if (tokens.length >= 13) {
            // Extract data for creating a new person from the script arguments
            String name = tokens[3];
            Integer coordinateX = Integer.parseInt(tokens[4]);
            Integer coordinateY = Integer.parseInt(tokens[5]);
            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
            Float height = Float.parseFloat(tokens[6]);
            EyeColor eyeColor = tokens[7].equals("null") ? null : EyeColor.valueOf(tokens[7]);
            HairColor hairColor = tokens[8].equals("null") ? null : HairColor.valueOf(tokens[8]);
            Country nationality = tokens[9].equals("null") ? null : Country.valueOf(tokens[9]);
            String locationName = tokens[10];
            int locationX = Integer.parseInt(tokens[11]);
            Float locationY = Float.parseFloat(tokens[12]);
            Float locationZ = tokens[13].equals("null") ? null : Float.parseFloat(tokens[13]);
            // Create and add the person to the collection
            Location location = new Location(locationName, locationX, locationY, locationZ);
            Person person = new Person(name, coordinates, height, eyeColor, hairColor, nationality, location);
            User user = new User(tokens[1], tokens[2]);
            if (dataBaseManager.addPerson(person, user) > 0) {
                PersonManager.addPers(person);
                System.out.printf("Person %s added to collection. Size of collection: %d%n", person.getName(), set.size());
            } else {
                System.out.println("Add failed");
            }
        } else {
            System.out.println("Not enough arguments to create a person.");
        }
    }


}
