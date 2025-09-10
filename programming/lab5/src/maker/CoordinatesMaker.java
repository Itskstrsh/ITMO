package maker;

import entity.Coordinates;

import java.util.Scanner;

/**
 * The CoordinatesMaker class provides a method to create coordinates based on user input.
 */
public class CoordinatesMaker {

    /**
     * Creates coordinates by prompting the user to input x and y values.
     *
     * @param scanner the Scanner object used for user input
     * @return a Coordinates object with the specified x and y values
     */
    public static Coordinates make(Scanner scanner) {
        Integer x;
        Integer y;

        // Prompt user for x value until a valid input is received
        while (true) {
            try {
                System.out.print("Введите (Integer) x: ");
                x = Integer.parseInt(scanner.next());
                if (x < -436) {
                    System.out.println("Значение должно быть больше -436.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите значение типа int!");
            }
        }

        // Prompt user for y value until a valid input is received
        while (true) {
            try {
                System.out.print("Введите (Integer) y: ");
                y = Integer.parseInt(scanner.next());
                if (y < -471) {
                    System.out.println("Значение должно быть больше -471.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите значение типа int!");
            }
        }

        return new Coordinates(x, y);
    }
}
