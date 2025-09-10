package data;

public class AreaChecker {
    public static boolean isInArea(double x, double y, double r) {
        return (r > 0) && (isInSquare(x, y, r) || isInTriangle(x, y, r) || isInCircle(x, y, r));
    }
    private static boolean isInSquare(double x, double y, double r) {
        return (x >= -r && x <= 0) && (y >= -r && y <= 0);
    }
    private static boolean isInTriangle(double x, double y, double r) {
        return (x >= 0 && y <= 0 && y >= -0.5 * (r - x));
    }
    private static boolean isInCircle(double x, double y, double r) {
        return (x <= 0 && y >= 0) && (x * x + y * y <= (r / 2) * (r / 2));
    }
}
