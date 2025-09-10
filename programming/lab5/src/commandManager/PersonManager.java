package commandManager;

import entity.Person;

import java.util.Date;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * The PersonManager class manages a collection of Person objects.
 */
public class PersonManager {
    private static TreeSet<Person> collection;
    private static PersonManager instance;
    private Date initializationDate;

    private PersonManager() {
        collection = new TreeSet<>(PersonManager.idComparator());
        initializationDate = new Date();
    }

    /**
     * Retrieves the singleton instance of PersonManager.
     *
     * @return the singleton instance of PersonManager
     */
    public static PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return instance;
    }

    /**
     * Retrieves the collection of Person objects.
     *
     * @return the collection of Person objects
     */
    public TreeSet<Person> getCollection() {
        return collection;
    }

    /**
     * Sets the collection of Person objects managed by the PersonManager.
     *
     * @param collection the TreeSet collection of Person objects to set
     */
    public static void setCollection(TreeSet<Person> collection) {
        PersonManager.collection = collection;
    }

    /**
     * Retrieves the initialization date of the PersonManager.
     *
     * @return the initialization date of the PersonManager
     */
    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Adds a Person object to the collection.
     *
     * @param person the Person object to add
     */
    public void addElement(Person person) {
        collection.add(person);
    }

    /**
     * Filters Person objects by nationality and returns a sorted TreeSet.
     *
     * @param nationalityValue the nationality value to filter by
     * @return a sorted TreeSet of filtered Person objects
     */
    public static TreeSet<Person> filterPersonsByNationality(String nationalityValue) {
        TreeSet<Person> filteredPersons = new TreeSet<>(Person.nationalityComparator());
        for (Person person : collection) {
            if (person.getNationality().toString().compareTo(nationalityValue) > 0) {
                filteredPersons.add(person);
            }
        }
        return filteredPersons;
    }

    /**
     * Compare persons by id.
     *
     * @return a comparator for comparing persons by id
     */
    public static Comparator<Person> idComparator() {
        return Comparator.comparingInt(Person::getId);
    }
}

