package data;

import data.generators.IdGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    @Serial
    private static final long serialVersionUID=100L;
    private int id; // This field cannot be null. The value of this field must be greater than 0. The value of this field must be unique. The value of this field must be generated automatically.
    private String name; // This field cannot be null. The string cannot be empty.
    private Coordinates coordinates; // This field cannot be null.
    private Date creationDate = new Date(); // This field cannot be null. The value of this field must be generated automatically.
    private Double height; // This field cannot be null. The value of this field must be greater than 0.
    private EyeColor eyeColor; // This field can be null.
    private HairColor hairColor; // This field can be null.
    private Country nationality; // This field can be null.
    private Location location; // This field cannot be null.

    /**
     * Constructs a new Person object with the specified parameters.
     *
     * @param name        the name of the person
     * @param coordinates the coordinates of the person
     * @param height      the height of the person
     * @param eyeColor    the eye color of the person
     * @param hairColor   the hair color of the person
     * @param nationality the nationality of the person
     * @param location    the location of the person
     */
    public Person(String name, Coordinates coordinates, Double height, EyeColor eyeColor, HairColor hairColor, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
        this.creationDate = Date.from(Instant.now());
    }
    public Person(int id) {
        this.id = id;
        this.name = null;
        this.coordinates = null;
        this.height = null;
        this.eyeColor = null;
        this.hairColor = null;
        this.nationality = null;
        this.location = null;
        this.creationDate = Date.from(Instant.now());
    }
    public Person(){
        this.id = IdGenerator.generateId();
        this.creationDate = Date.from(Instant.now());
    }

    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the height of the person.
     *
     * @return the height of the person
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Retrieves the id of the person.
     *
     * @return the id of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the nationality of the person.
     *
     * @return the nationality of the person
     */
    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns a comparator for comparing persons by nationality.
     *
     * @return a comparator for comparing persons by nationality
     */
    public static Comparator<Person> nationalityComparator() {
        return (p1, p2) -> p1.nationality.compareTo(p2.nationality);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }


    public void setHeight(Double height) {
        this.height = height;
    }


    /**
     * Compares this person with another person based on their names.
     *
     * @param o the person to be compared
     * @return a negative integer, zero, or a positive integer as this person is less than, equal to, or greater than the specified person
     */
    @Override
    public int compareTo(Person o) {
        Integer id = this.id;
        return id.compareTo(o.getId());
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime creationDateLocal = LocalDateTime.ofInstant(creationDate.toInstant(), ZoneId.systemDefault());
        return String.format("name='%s'{%n" +
                "  id=%d,%n" +
                "  coordinates=%s,%n" +
                "  creationDate=%s,%n" +
                "  height=%.2f,%n" +
                "  eyeColor=%s,%n" +
                "  hairColor=%s,%n" +
                "  nationality='%s',%n" +
                "  location='%s',%n" +
                "}",name, id, coordinates, creationDateLocal.format(formatter), height, eyeColor, hairColor, nationality, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

