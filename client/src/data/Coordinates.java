package data;

/**
 * The Coordinates class represents a pair of coordinates.
 */
public class Coordinates {
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

    @Override
    public String toString() {
        return String.format("{%n" +
                "  x=%s,%n" +
                "  y=%s" + "}", x, y);
    }
}