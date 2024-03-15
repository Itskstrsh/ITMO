package matter;

import enums.TypeOfTransport;

import java.util.Objects;

public class Transport extends Item {
    TypeOfTransport type;
    int damage;

    public Transport(String name) {
        super(name);
    }

    public int getDamage(int damage) {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
        if (damage > 5) {
            System.out.print(this.name + " лопнул");
        } else {
            System.out.print(this.name + " полетел");
        }
    }

    public TypeOfTransport getType() {
        return type;
    }

    private void setType(TypeOfTransport type) {
        this.type = type;
        System.out.printf(this.name, type.getIncase());
    }

    public static class Engine {
        boolean isWork = false;
        public void isWorking() {
            if (this.isWork) {
                System.out.println(", транспорт работает");
            } else {
                System.out.print(", транспорт не работает");
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Entity)) return false;
        Entity transport = (Entity) obj;
        return Objects.equals(transport.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}