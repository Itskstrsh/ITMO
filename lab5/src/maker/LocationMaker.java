package maker;

import entity.Location;

import java.util.Scanner;

/**
 * The LocationMaker class provides a method to create a location object based on user input.
 */
public class LocationMaker {

    /**
     * Prompts the user to input details for creating a location object.
     *
     * @param scanner the Scanner object used for user input
     * @return the created Location object
     */
    public static Location make(Scanner scanner) {
        String name;
        int x;
        double y;
        Float z;

        // Prompt user for location name
        while (true) {
            System.out.print("Введите имя локации: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                return null;
            } else {
                break;
            }
        }

        // Prompt user for x coordinate
        while (true) {
            try {
                System.out.print("Введите (int) x координату для локации: ");
                x = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите значение int!");
            }
        }

        // Prompt user for y coordinate
        while (true) {
            try {
                System.out.print("Введите (double) y координату для локации: ");
                y = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите значение тип double!");
            }
        }

        // Prompt user for z coordinate
        while (true) {
            try {
                System.out.print("Введите  (Float) z координату для локации: ");
                z = Float.parseFloat(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите значение типа float!");
            }
        }

        return new Location(name, x, y, z);
    }
}
