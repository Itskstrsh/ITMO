import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Response {
    public int x;
    public float y;
    public int r;
    public boolean answer;
    public float executionTime;
    public String curTime;
    public Response(int x, float y, int r){
        this.x = x;
        this.y=y;
        this.r=r;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        curTime = formatter.format(LocalDateTime.now());
        Instant executionStart = Instant.now();
        this.answer = Answer.IsOK(x,y,r);
        Instant executionEnd = Instant.now();
        executionTime = (Duration.between(executionStart,executionEnd).toNanos());
    }
    public static Response newResponse(Request request){
        return new Response(request.x,request.y, request.r);
    }

    @Override
    public String toString() {
        return "Response{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", answer=" + answer +
                ", executiontime=" + executionTime +
                ", curTime='" + curTime + '\'' +
                '}';
    }
}
