import com.fastcgi.FCGIInterface;
import com.google.gson.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            String jRequest;
            try{
                jRequest=readRequestBody();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Request request = gson.fromJson(jRequest, Request.class);
            Response response = Response.newResponse(request);
            String jResponse = gson.toJson(response,Response.class);
            var httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    
                    %s
                    """.formatted(jResponse);
            System.out.println(httpResponse);
        }
    }

    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                FCGIInterface.request.inStream.read(buffer.array(), 0,
                        contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}