package matter;

import java.util.Objects;

public class Plant extends Item {
    int height;
    public Plant(String name) {
        super(name);
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }
    public void grow(int height){
        this.height++;
        System.out.println("растение растёт");
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Entity)) return false;
        Entity plant = (Entity) obj;
        return Objects.equals(plant.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
