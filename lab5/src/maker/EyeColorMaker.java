package maker;

import entity.EyeColor;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The EyeColorMaker class provides a method to select an eye color based on user input.
 */
public class EyeColorMaker {

    /**
     * Prompts the user to select an eye color from a predefined list.
     *
     * @param scanner the Scanner object used for user input
     * @return the selected EyeColor object, or null if no eye color is selected
     */
    public static EyeColor getEyeColor(Scanner scanner) {
        while (true) {
            System.out.println("Введите номер для выбора цвета глаз:");
            System.out.println("1-RED\n2-ORANGE\n3-BROWN\n4-null");
            try {
                int eyeChoice = scanner.nextInt();
                scanner.nextLine();
                if (eyeChoice < 1 || eyeChoice > 4) {
                    System.out.println("Неверный ввод. Попробуйте еще раз.");// Invalid choice, loop continues
                } else {
                    switch (eyeChoice) {
                        case 1:
                            return EyeColor.RED;
                        case 2:
                            return EyeColor.ORANGE;
                        case 3:
                            return EyeColor.BROWN;
                        case 4:
                            return null;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Введите значение int!");
                scanner.nextLine();
            }
        }
    }

}

