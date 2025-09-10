public class Request {
    public int x;
    public float y;
    public int r;
    public Request(int x, float y, int r){
        this.x = x;
        this.y=y;
        this.r=r;
    }
    @Override
    public String toString() {
        return "Request{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}
