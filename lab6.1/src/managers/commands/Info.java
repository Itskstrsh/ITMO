package managers.commands;

import data.Person;
import managers.PersonManager;

import java.util.Date;
import java.util.TreeSet;

public class Info implements Command{


    @Override
    public void execute(String[] args) {
        TreeSet<Person> set = PersonManager.getCollection();
        Date date = PersonManager.getDate();
        System.out.printf("Тип коллекции: %s\n", set.getClass());
        System.out.printf("Дата инициализации: %s\n", date == null ? "Коллекция не инициализирована" : date);
        System.out.printf("Количество элементов: %s\n", set.size());
    }
}
