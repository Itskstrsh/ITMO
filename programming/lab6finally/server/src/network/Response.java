package network;

import java.io.Serial;
import java.io.Serializable;

public class
Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1023013L;

    private String result = "\nSuccess\n";

    public Response(String result){
        this.result=result;
    }
    public Response(){
    }
    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
