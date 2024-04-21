package data;

/**
 * The Location class represents a geographical location with coordinates and a name.
 */
public class Location {
    private int x;
    private double y; //Поле не может быть null
    private Float z;
    private String name; //Строка не может быть пустой, Поле может быть null

    /**
     * Constructs a new Location object with the specified parameters.
     *
     * @param name the name of the location
     * @param x the x-coordinate value
     * @param y the y-coordinate value
     * @param z the z-coordinate value
     */
    public Location(String name, int x, double y, Float z){
        this.name=name;
        this.x=x;
        this.y=y;
        this.z=z;
    }

    @Override
    public String toString() {
        return String.format("%n  name='%s'{%n" +
                "  x=%s,%n" +
                "  y=%s,%n" +
                "  z=%s"  +  "}",name,x,y,z);
    }
}
