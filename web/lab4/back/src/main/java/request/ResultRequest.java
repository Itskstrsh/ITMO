package request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultRequest {
    private double x;
    private double y;
    private double r;
    private Long userId;



    @Override
    public String toString() {
        return "ResultRequest{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", userId='" + userId + '\'' +
                '}';
    }
}
