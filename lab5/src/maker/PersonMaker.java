package maker;

import java.util.Scanner;

import entity.*;

/**
 * The PersonMaker class provides a method to create a Person object with specified attributes.
 */
public class PersonMaker {

    /**
     * Creates a Person object based on user input for various attributes.
     *
     * @param scanner the Scanner object used for user input
     * @return the created Person object
     */
    public static Person make(Scanner scanner) {
        System.out.println("Создание персонажа...");
        String name = getName(scanner);
        Double height = getHeight(scanner);
        Location location = LocationMaker.make(scanner);
        HairColor hairColor = HairColorMaker.make(scanner);
        EyeColor eyeColor = EyeColorMaker.getEyeColor(scanner);
        Country country = CountryMaker.getCountry(scanner);
        Coordinates coordinates = CoordinatesMaker.make(scanner);
        return new Person(name, coordinates, height, eyeColor, hairColor, country, location);
    }

    /**
     * Prompts the user to enter a name for the person.
     *
     * @param scanner the Scanner object used for user input
     * @return the name entered by the user
     */
    private static String getName(Scanner scanner) {
        while (true) {
            System.out.print("Введите имя персонажа: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Вы ввели пустую строку!");
            } else {
                return name;
            }
        }
    }

    /**
     * Prompts the user to enter the height of the person.
     *
     * @param scanner the Scanner object used for user input
     * @return the height entered by the user
     */
    private static Double getHeight(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите рост: ");
                Double height = Double.parseDouble(scanner.nextLine());
                if (height <= 0) {
                    System.out.println("Вы ввели отрицательный рост!!");
                } else {
                    return height;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите значение типа Double!");
            }
        }
    }
}
