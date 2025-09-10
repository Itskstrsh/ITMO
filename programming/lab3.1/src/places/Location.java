package places;

public class Location {
    String name;

    public Location(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Location)) return false;
        Location loc = (Location) obj;
        return (loc.name == this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}