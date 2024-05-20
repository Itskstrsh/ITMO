package managers;

import data.*;
import exceptions.UserExistsException;
import network.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class DataBaseManager {
    private QueryManager queryManager = new QueryManager();

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7", "postgres", "root");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error with connection to DataBase");
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) throws UserExistsException {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.checkUser);
            preparedStatement.setString(1,user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                throw new UserExistsException();
            }
            PassWordHasherManager passwordHasherManager = new PassWordHasherManager();
            String salt = saltGenerator();
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt);
            PreparedStatement pr = connection.prepareStatement(queryManager.addUser);
            pr.setString(1, user.getLogin());
            pr.setString(2, password);
            pr.setString(3, salt);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении запроса");
            e.printStackTrace();
        }
    }

    public int addPerson(Person person, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            PassWordHasherManager passWordHasherManager = new PassWordHasherManager();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1, user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()) {
                return 0;
            }
            String salt = getSaltResult.getString(1);
            String password = passWordHasherManager.hashPassword(user.getPassword() + salt);
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.addPerson);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getCoordinates().getX());
            preparedStatement.setInt(3, person.getCoordinates().getY());
            preparedStatement.setFloat(4, person.getHeight());
            preparedStatement.setString(5, person.getEyeColor().toString());
            preparedStatement.setString(6, person.getHairColor().toString());
            preparedStatement.setString(7, person.getNationality().toString());
            preparedStatement.setString(8, person.getLocation().getName());
            preparedStatement.setInt(9, person.getLocation().getX());
            preparedStatement.setFloat(10, person.getLocation().getY());
            preparedStatement.setFloat(11, person.getLocation().getZ());
            preparedStatement.setString(12, person.getCreationDate().toString());
            preparedStatement.setInt(13, user_id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.err.println("Error with adding new object");
                return -1;
            }
            System.err.println("Object added successfully");
            return resultSet.getInt(1);
        } catch (SQLException e) {
            System.err.println("Request execution error");
            return -1;
        }
    }

    private String saltGenerator() {
        String alphabet = "qwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public boolean existUser(User user) {
        Connection connection = connect();
        try {
            PassWordHasherManager passwordHasherManager = new PassWordHasherManager();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1, user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()) {
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt);
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
        }
        return false;
    }

    public boolean deleteObject(int id, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            PassWordHasherManager passwordHasherManager = new PassWordHasherManager();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1, user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()) {
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt);
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.deleteObject);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setLong(2, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean updateObjects(Integer id, User user, Person person) {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.updatePerson);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getCoordinates().getX());
            preparedStatement.setInt(3, person.getCoordinates().getY());
            preparedStatement.setString(4, person.getCreationDate().toString());
            preparedStatement.setDouble(5, person.getHeight());
            preparedStatement.setString(6, person.getEyeColor().toString());
            preparedStatement.setString(7, person.getHairColor().toString());
            preparedStatement.setString(8, person.getNationality().toString());
            preparedStatement.setInt(9, person.getLocation().getX());
            preparedStatement.setDouble(10, person.getLocation().getY());
            preparedStatement.setFloat(11, person.getLocation().getZ());
            preparedStatement.setString(12, person.getLocation().getName());
            preparedStatement.setString(13, user.getLogin());
            preparedStatement.setInt(14, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public TreeSet<Person> loadCollection() {
        Connection connection = connect();
        TreeSet<Person> persons = new TreeSet<>();
        try {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryManager.addObjects);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    persons.add(new Person(resultSet.getInt(1), resultSet.getString(2),
                            new Coordinates(resultSet.getInt(3), resultSet.getInt(4)),
                            (float) resultSet.getDouble(5),
                            EyeColor.valueOf(resultSet.getString(6)),
                            HairColor.valueOf(resultSet.getString(7)),
                            Country.valueOf(resultSet.getString(8)),
                            new Location(resultSet.getString(9), resultSet.getInt(10), (float) resultSet.getDouble(11), (float) resultSet.getDouble(12)),
                            resultSet.getString(13)));
                }
                return persons;
            } catch (SQLException e) {
                System.err.println("Request execution error");
                return new TreeSet<>();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Object fields are not valid");
            return new TreeSet<>();
        }
    }

    public List<String> showObjects(User user) {
        Connection connection = connect();
        List<String> persons = new ArrayList<>();
        int user_id = 0;
        try {
            PassWordHasherManager passWordHasherManager = new PassWordHasherManager();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1, user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()) {
                return new ArrayList<>();
            }
            String salt = getSaltResult.getString(1);
            String password = passWordHasherManager.hashPassword(user.getPassword() + salt);
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.getObjects);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                persons.add(String.valueOf(new Person(resultSet.getInt(1), resultSet.getString(2),
                        new Coordinates(resultSet.getInt(3), resultSet.getInt(4)),
                        (float) resultSet.getDouble(5),
                        EyeColor.valueOf(resultSet.getString(6)),
                        HairColor.valueOf(resultSet.getString(7)),
                        Country.valueOf(resultSet.getString(8)),
                        new Location(resultSet.getString(9), resultSet.getInt(10), (float) resultSet.getDouble(11), (float) resultSet.getDouble(12)),
                        resultSet.getString(13))));
            }
            return persons;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Objects are not validate");
            return new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}