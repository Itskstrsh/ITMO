package matter;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.OutOfListException;
import interfaces.*;
import enums.*;
import interfaces.Comparable;
import places.Location;

public abstract class Entity implements Movable, Comparable {
    String name;
    ArrayList<String> items = new ArrayList<String>();
    Emotion emotion;

    public Entity(String name, ArrayList<String> items) {
        this.name = name;
        if (items.size() > 4) {
            throw new OutOfListException("Сообщение: очень много вещей у персонажа");
        } else {
            this.items = items;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    private void setEmotion(Emotion emotion) {
        this.emotion = emotion;
        System.out.printf(this.name, emotion.getIncase());
    }

    @Override
    public void go(Location at, Transport how) {
        System.out.print(this.name + " добирается с помощью " + how.getName() + " в " + at.getName());
    }

    public String createQueue(ArrayList<Entity> entities) {
        compare(entities, "компас");

        StringBuilder output = new StringBuilder();
        output.append("Впереди всех шел ").append(entities.get(0).getName()).append(" с компасом в руках");
        for (int i = 1; i < entities.size(); i++) {
            output.append(", за ним ").append(entities.get(i).getName());
        }
        output.append(", а за ними остальные малыши-коротыши");
        return output.toString();
    }

    @Override
    public String compare(ArrayList<Entity> entities, String compareWord) {
        entities.sort((e1, e2) -> {
            boolean e1HasCompass = e1.getItems().contains(compareWord);
            boolean e2HasCompass = e2.getItems().contains(compareWord);

            if (e1HasCompass && !e2HasCompass) {
                return -1;
            } else if (!e1HasCompass && e2HasCompass) {
                return 1;
            } else {
                return e1.getName().compareTo(e2.getName());
            }
        });
        return compareWord;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Entity)) return false;
        Entity e2 = (Entity) obj;
        return Objects.equals(e2.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}