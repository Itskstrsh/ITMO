package data;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Coordinates class represents a pair of coordinates.
 */
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID=102L;

    private Integer x; //Значение поля должно быть больше -436
    private Integer y; //Значение поля должно быть больше -471

    /**
     * Constructs a new Coordinates object with the specified x and y values.
     *
     * @param x the x-coordinate value
     * @param y the y-coordinate value
     */
    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{%n" +
                "  x=%s,%n" +
                "  y=%s" + "}", x, y);
    }
}