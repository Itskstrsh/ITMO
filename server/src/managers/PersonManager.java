package managers;

import data.Person;
import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import exceptions.ReplayIdException;
import exceptions.WrongArgumentException;
import managers.commands.Command;

import network.Request;
import network.Response;

import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.*;

public class PersonManager {
    static TreeSet<Person> collection;
    private TreeSet<Person> persons = new TreeSet<>();
    private DataBaseManager dataBaseManager = new DataBaseManager();
    static Date date;

    public PersonManager() {
        collection = new TreeSet<>();
        date = Date.from(Instant.now());
    }

    public static void setCollection(TreeSet<Person> collection) {
        PersonManager.collection = collection;
    }

    public static TreeSet<Person> getCollection() {
        return collection;
    }

    public static Response addPers(Person person) {
        try {
            Validator.coordinateXIsOk(Integer.toString(person.getCoordinates().getX()));
            Validator.coordinateYIsOk(Integer.toString(person.getCoordinates().getY()));
            Validator.locationXIsOk(Integer.toString(person.getLocation().getX()));
            Validator.locationYIsOk(Float.toString(person.getLocation().getY()));
            Validator.locationZIsOk(Float.toString(person.getLocation().getZ()));
            Validator.heightIsOk(Float.toString(person.getHeight()));
            Validator.idIsOk(Integer.toString(person.getId()));
        } catch (WrongArgumentException | ReplayIdException e) {
            return new Response(e.getMessage());
        }
        if (collection == null) {
            collection = new TreeSet<>();
        }
        collection.add(person);
        IdGenerator.add(person.getId());
        return new Response("Person added successfully");
    }

    public static TreeSet<Person> filterPersonsByNationality(String nationalityValue) {
        TreeSet<Person> filteredPersons = new TreeSet<>(Person.nationalityComparator());
        for (Person person : collection) {
            if (person.getNationality().toString().compareTo(nationalityValue) > 0) {
                filteredPersons.add(person);
            }
        }
        return filteredPersons;
    }

    public void setPerson(TreeSet<Person> settedPersons) {
        this.persons = settedPersons;
    }

    public static Response help() {
        return new Response("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "remove_all_by_height height : удалить из коллекции все элементы, значение поля height которого эквивалентно заданному\n" +
                "filter_greater_than_nationality nationality : вывести элементы, значение поля nationality которых больше заданного\n" +
                "print_descending : вывести элементы коллекции в порядке убывания\n" +
                "registration : зарегистрировать пользователя\n" +
                "authorisation : авторизация пользователя\n" +
                "get_user : получить логин текущего пользователя\n" +
                "exit_from_account : выйти из аккаунта");
    }

    public static Response addIfMax(Person person) {
        person = PersonGenerator.createPerson(IdGenerator.generateId());
        TreeSet<Person> set = PersonManager.getCollection();
        if (set.last().compareTo(person) == -1) {
            set.add(person);
            return new Response("New person added to collection: " + person);
        } else {
            return new Response("Nothing changed");
        }
    }

    public static Response filterGTN(String[] args) {
        try {
            String nationalityValue = args[2];
            TreeSet<Person> filteredPersons = PersonManager.filterPersonsByNationality(nationalityValue);
            System.out.println("Persons whose nationality field value is greater than the specified one: ");
            for (Person person : filteredPersons) {
                System.out.println(person.getName());
            }
        } catch (Exception e) {
            System.out.println("Input a String value");
        }
        return filterGTN(args);
    }

    public static Response getInfo() {
        return new Response("Stored type: " + Person.class +
                "\nNumber of Persons: " + collection.size() +
                "\nDate of creation: " + date + "\n");
    }

    public static Response printDesc() {
        var res = new StringBuilder();
        collection.stream().sorted(Comparator.reverseOrder()).forEach(person -> res.append(person).append("\n"));
        return new Response(res.toString());
    }

    public static Response removeABH(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        try {
            Double height = Double.parseDouble(args[1]);
            boolean containsPersonWithHeight = set.stream().anyMatch(person -> Objects.equals(person.getHeight(), height));
            if (containsPersonWithHeight) {
                set.removeIf(person -> Objects.equals(person.getHeight(), height));
                System.out.println("Person with height: " + height + " -     is deleted");
            } else {
                System.out.println("No person with such height: " + height);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input integer value");
        }
        return null;
    }

    public static Response show() {
        if (collection.isEmpty())
            return new Response("Collection is empty");
        var res = new StringBuilder();
        collection.forEach(person -> res.append(person).append("\n"));
        return new Response(res.toString());
    }

    public static Response removeById(Integer id) {
        for (Person person : collection) {
            if (person.getId() == id) {
                collection.remove(person);
                break;
            }
        }
        return new Response("Collection cleared");
    }

    public static Response removeLower(Person newperson) {
        if (collection.removeIf(person -> person.compareTo(newperson) < 0)) return new Response("Persons deleted");
        else return new Response("Nothing changed");
    }

    public static Response updateId(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        try {
            int idToUpdate = Integer.parseInt(args[1]);
            boolean containsPersonWithId = set.stream().anyMatch(person -> person.getId() == idToUpdate);
            if (!containsPersonWithId) {
                return new Response("No person with such id");
            } else {
                Person newPerson = PersonGenerator.createPerson(IdGenerator.generateId());
                set.stream()
                        .filter(person -> person.getId() == idToUpdate)
                        .findFirst()
                        .ifPresent(set::remove);
                set.add(newPerson);
                System.out.println("Person with ID: " + idToUpdate + " is updated");
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect Id");
        }
        return new Response(set.toString());
    }
}
