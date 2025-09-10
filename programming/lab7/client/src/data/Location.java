package data;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Location class represents a geographical location with coordinates and a name.
 */
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID=101L;

    private int x;
    private Float y; //Поле не может быть null
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
    public Location(String name, int x, Float y, Float z){
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
