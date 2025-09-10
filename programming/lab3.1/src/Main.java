import enums.Emotion;
import enums.Luggage;
import enums.Time;
import enums.TypeOfTransport;
import exceptions.*;
import interfaces.Comparable;
import interfaces.Movable;
import matter.*;
import places.Location;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //1

        ArrayList<String> itemsZnaikaAndGroup = new ArrayList<String>();
        itemsZnaikaAndGroup.add("компас");
        itemsZnaikaAndGroup.add("ручка");
        itemsZnaikaAndGroup.add("бумажка");
        itemsZnaikaAndGroup.add("спичка");
        Shorty ZnaikaAndGroup = new Shorty("Знайка и его товарищи", itemsZnaikaAndGroup);
        Time next = Time.TOMMOROW;
        System.out.print(next.getIncase());
        ZnaikaAndGroup.toGoWithGroup();
        System.out.print(". ");

        //2
        System.out.print("Решили путешествовать");
        TypeOfTransport leg = TypeOfTransport.LEG;
        System.out.print(leg.getIncase());
        System.out.print(". ");

        //3
        Transport shar = new Transport("Воздушный шар");
        shar.setDamage(6);
        Transport.Engine motor = new Transport.Engine();
        motor.isWorking();
        System.out.println(". ");

        //4
        ArrayList<String> itemsZnaika = new ArrayList<String>();
        itemsZnaika.add("компас");
        itemsZnaika.add("бутылка воды");
        Shorty znaika = new Shorty("Знайка", itemsZnaika);
        ArrayList<String> items_Pilulya = new ArrayList<String>();
        items_Pilulya.add("рюкзак");
        items_Pilulya.add("конфета");
        Shorty pilylya = new Shorty("доктор Пилюлькин", items_Pilulya);
        ArrayList<String> itemsVint = new ArrayList<String>();
        itemsVint.add("рюкзак");
        itemsVint.add("конфета");
        itemsVint.add("шоколад");
        Shorty vintik = new Shorty("Винтик", itemsVint);
        ArrayList<String> itemsShpuntik = new ArrayList<String>();
        itemsShpuntik.add("нож");
        itemsShpuntik.add("пистолет");
        itemsShpuntik.add("телевизор");
        Shorty shpuntik = new Shorty("Шпунтик", itemsShpuntik);
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(znaika);
        entities.add(pilylya);
        entities.add(vintik);
        entities.add(shpuntik);
        String result = pilylya.createQueue(entities);
        System.out.print(result);
        System.out.println(". ");


        //5
        ArrayList<String> itemsneZnaika = new ArrayList<String>();
        itemsneZnaika.add("сумка");
        itemsneZnaika.add("палатка");
        Shorty neznaika = new Shorty("Нeзнайка", itemsneZnaika);
        neznaika.goBehind();
        System.out.print(". ");
        //6
        Thing bag = new Thing("сумка");
        System.out.print("У каждого за спиной была ");
        System.out.print(bag.getName());
        System.out.print(". ");

        //7
        ArrayList<String> itemsMalishki = new ArrayList<String>();
        itemsMalishki.add("ничего");
        itemsMalishki.add("палатка");
        Shorty malishki = new Shorty("Малышки", itemsMalishki);
        malishki.toSew(bag);
        System.out.print(". ");

        //8
        Location city = new Location("Цветочный город");
        Thing food = new Thing("еда") {
            public String getName() {
                return (" пироги, семена фруктов, овощей и цветов");

            }
        };

        Luggage sumka = Luggage.BAG;
        System.out.print(sumka.getIncase());
        System.out.print(food.getName());
        System.out.print(", которых не было в ");
        System.out.println(city.getName());

        //9
        ArrayList<String> itemSyrop = new ArrayList<String>();
        itemSyrop.add("косточка");
        itemSyrop.add("зажигалка");
        Shorty syropchik = new Shorty("Сиропчик", itemSyrop);
        Thing kostochka = new Thing("арбузная косточка");
        syropchik.toHave(kostochka);
        Luggage karman = Luggage.POCKET;
        System.out.print(karman.getIncase());
        System.out.print(". ");

        //10
        ArrayList<String> itemsMalishi = new ArrayList<String>();
        itemsMalishi.add("ничего");
        Shorty malishi = new Shorty("малыши", itemsMalishi);
        malishki.toSayGoodbye(malishi);
        System.out.print(". ");

        //11
        malishki.toCry();
        System.out.println(". ");

        //12
        Transport byleg = new Transport("ног");
        ZnaikaAndGroup.go(city, byleg);
        System.out.print(" через поля и леса. ");

        //13
        Location xolm = new Location("высоком холме");
        ZnaikaAndGroup.toStop(xolm);
        System.out.print(", а спереди был виден ");
        System.out.print(city.getName());
        System.out.println(". ");

        //14
        try {
            matter.Time time = new matter.Time("current_time");
            Flower flowers = new Flower("белые хризантемы, красные георгины, разноцветные астры");
            time.time("лето", flowers);
        } catch (UnexpectedTimeException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        System.out.println(". ");

        //15
        Location backyard = new Location("дворах");
        Flower glaszki = new Flower("Анютины глазки");
        glaszki.dazzle(backyard);
        System.out.print(". ");

        //16
        Location wall = new Location("стены домов");
        Flower nastruciya = new Flower("Огненные наструции");
        nastruciya.curl(wall);
        System.out.println(". ");

        //17
        ZnaikaAndGroup.toHug(malishi);
        Emotion rad = Emotion.HAPPY;
        System.out.print(rad.getIncase());
        System.out.print(". ");

        //18
        malishi.toGo(city);
        System.out.println(". ");

        //19
        Normal normal = new Normal("жители", new ArrayList<>());
        System.out.print("Из всех домов выбегали ");
        normal.toSee(malishi);

        //20
        String skinDescription = ZnaikaAndGroup.getSkinDescription();
        System.out.println(skinDescription);
    }
}