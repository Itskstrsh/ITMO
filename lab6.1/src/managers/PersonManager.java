package managers;

import data.Person;
import data.generators.IdGenerator;

import java.time.Instant;
import java.util.Date;
import java.util.TreeSet;

public class PersonManager {
    static TreeSet<Person> collection;
    static Date date;
    public PersonManager(){
        collection = new TreeSet<>();
        date = Date.from(Instant.now());
    }
    public static Date getDate(){
        return date;
    }

    public static void setCollection(TreeSet<Person> collection) {
        PersonManager.collection = collection;
    }

    public static TreeSet<Person> getCollection() {
        return collection;
    }
    public static void add(Person person){
        if(collection == null){
            collection = new TreeSet<>();
        }
        collection.add(person);
        IdGenerator.add(person.getId());
        System.out.println("Person added successfully");
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
}
