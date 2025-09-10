package response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultResponse {
    private double x;
    private double y;
    private double r;
    private boolean hit;

    public ResultResponse(double x, double y, double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

}
