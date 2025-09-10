package data.generators;

import data.*;
import exceptions.WrongArgumentException;
import managers.Validator;

import java.util.Scanner;

public class PersonGenerator {
    public static Person createPerson(int id) {
        System.out.println("Creating Person...");
        Scanner scanner = new Scanner(System.in);
        String input, name, x, y, z, xc, yc;
        Location location;
        Coordinates coordinates;
        Person person;
        if (id == 0) {
            person = new Person();
        } else {
            person = new Person(id);
        }
        while (true) {
            try {
                System.out.print("Input name(String): ");
                input = scanner.nextLine();
                Validator.inputIsNotEmpty(input, "NAME");
                person.setName(input);
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input coordinate x(int) > -436: ");
                input = scanner.nextLine();
                Validator.coordinateXIsOk(input);
                xc = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input coordinate y(int) > -471: ");
                input = scanner.nextLine();
                Validator.coordinateYIsOk(input);
                yc = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        coordinates = new Coordinates(Integer.parseInt(xc), Integer.parseInt(yc));
        person.setCoordinates(coordinates);
        while (true) {
            try {
                System.out.print("Input height(Double): ");
                input = scanner.nextLine();
                Validator.heightIsOk(input);
                person.setHeight(Float.valueOf(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input number for eye color type: ");
                System.out.println("\n1-RED\n2-ORANGE\n3-BROWN");
                input = scanner.nextLine();
                Validator.eyeTypeIsOk(input);
                EyeColor eyeColor = switch (Integer.parseInt(input)) {
                    case 1 -> EyeColor.RED;
                    case 2 -> EyeColor.ORANGE;
                    case 3 -> EyeColor.BROWN;
                    default -> null;
                };
                person.setEyeColor(eyeColor);
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input number for hair color type: ");
                System.out.println("\n1-GREEN\n2-BLUE\n3-WHITE\n4-BROWN");
                input = scanner.nextLine();
                Validator.hairTypeIsOk(input);
                HairColor hairColor = switch (Integer.parseInt(input)) {
                    case 1 -> HairColor.GREEN;
                    case 2 -> HairColor.BLUE;
                    case 3 -> HairColor.WHITE;
                    case 4 -> HairColor.BROWN;
                    default -> null;
                };
                person.setHairColor(hairColor);
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input number for nationality type: ");
                System.out.println("\n1-FRANCE\n2-CHINA\n3-SOUTH_KOREA");
                input = scanner.nextLine();
                Validator.countryTypeIsOk(input);
                Country country = switch (Integer.parseInt(input)) {
                    case 1 -> Country.FRANCE;
                    case 2 -> Country.CHINA;
                    case 3 -> Country.SOUTH_KOREA;
                    default -> null;
                };
                person.setNationality(country);
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input location name(String): ");
                input = scanner.nextLine();
                Validator.inputIsNotEmpty(input, "NAME");
                name = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input location coordinate x(int): ");
                input = scanner.nextLine();
                Validator.locationXIsOk(input);
                x = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input location coordinate y(float): ");
                input = scanner.nextLine();
                Validator.locationYIsOk(input);
                y = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Input location coordinate z(float): ");
                input = scanner.nextLine();
                Validator.locationZIsOk(input);
                z = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        location = new Location(name, Integer.parseInt(x), Float.parseFloat(y), Float.parseFloat(z));
        person.setLocation(location);
        System.out.println("Generating...");
        return person;
    }
}
