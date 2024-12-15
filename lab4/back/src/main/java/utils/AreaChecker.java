package utils;

public class AreaChecker {
    public static boolean checkHit(double x, double y, double r) {
        boolean inRectangle = (x >= 0) && (y >= 0) && (x <= r) && (y <= r);
        boolean inTriangle = (x <= 0) && (x >= -0.5) && (y >= 0) && (y <= -2 * x + 1);
        boolean inCircle = (x <= 0) && (y <= 0) && (x * x + y * y <= (r / 2) * (r / 2));
        return inRectangle || inTriangle || inCircle;
    }

}
