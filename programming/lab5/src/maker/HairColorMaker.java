package maker;

import entity.HairColor;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The HairColorMaker class provides a method to select a hair color based on user input.
 */
public class HairColorMaker {

    /**
     * Prompts the user to select a hair color from a predefined list.
     *
     * @param scanner the Scanner object used for user input
     * @return the selected HairColor object, or null if no hair color is selected
     */
    public static HairColor make(Scanner scanner) {
        while (true) {
            System.out.println("Введите значение для выбора цвета волос:");
            System.out.println("1-GREEN\n2-BLUE\n3-WHITE\n4-BROWN\n5-null");
            try {
                int hairChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                if (hairChoice < 1 || hairChoice > 5) {
                    // Invalid choice, loop continues
                    System.out.println("Неверный ввод. Попробуйте еще раз.");
                } else {
                    switch (hairChoice) {
                        case 1:
                            return HairColor.GREEN;
                        case 2:
                            return HairColor.BLUE;
                        case 3:
                            return HairColor.WHITE;
                        case 4:
                            return HairColor.BROWN;
                        case 5:
                            return null;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Неверный ввод. Попробуйте еще раз.");
                scanner.nextLine(); // consume invalid input
            }
        }
    }
}