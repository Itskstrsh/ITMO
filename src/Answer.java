public class Answer {
    public static boolean IsOK(int x, float y, int r){
        // 3 четверть
        if (x >= -r && x <= 0 && y >= -r && y <= 0) {
            return true;
        }
        // 4 четверть
        else if (x >= 0 && x <= r / 2 && y >= 0 && y <= r && y <= (-2 * x + r)) {
            return true;
        }
        //1 четверть
        else if (x >= 0 && y >= 0 && (x * x + y * y <= (r / 2.0) * (r / 2.0))) {
            return true;
        }
        // ничего
        return false;
    }
}
