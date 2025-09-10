package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xValue = request.getParameter("x_value");
        String yValue = request.getParameter("y_value");
        String yValueInput = request.getParameter("y_value_input");
        String radius = request.getParameter("radius");

        if ((yValue == null || yValue.isEmpty()) && yValueInput != null && !yValueInput.isEmpty()) {
            yValue = yValueInput;
        }

        if (xValue != null && yValue != null && radius != null && checkRequest(xValue, yValue, radius)) {
            request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static boolean checkRequest(String xValueStr, String yValueStr, String radiusStr) {
        try {
            float x = Float.parseFloat(xValueStr);
            float y = Float.parseFloat(yValueStr);
            double r = Double.parseDouble(radiusStr);

            return x >= -4 && x <= 4
                    && y >= -5 && y <= 5
                    && r >= 1 && r <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
