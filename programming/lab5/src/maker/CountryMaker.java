package maker;

import entity.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The CountryMaker class provides a method to select a country based on user input.
 */
public class CountryMaker {

    /**
     * Prompts the user to select a country from a predefined list.
     *
     * @param scanner the Scanner object used for user input
     * @return the selected Country object, or null if no country is selected
     */
    public static Country getCountry(Scanner scanner) {
        while (true) {
            System.out.println("Введите номер для выбора страны 1 to 4: ");
            System.out.println("1-FRANCE\n2-CHINA\n3-SOUTH_KOREA\n4-null");
            try {
                int cChoice = scanner.nextInt();
                scanner.nextLine();
                if (cChoice < 1 || cChoice > 4) {
                    System.out.println("Неверный ввод. Попробуйте еще раз.");
                } else {
                    switch (cChoice) {
                        case 1:
                            return Country.FRANCE;
                        case 2:
                            return Country.CHINA;
                        case 3:
                            return Country.SOUTH_KOREA;
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
