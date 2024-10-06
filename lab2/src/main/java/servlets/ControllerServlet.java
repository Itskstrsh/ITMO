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
        String yValue = request.getParameter("y_value_input");
        String radius = request.getParameter("radius");

        if (xValue != null && yValue != null && radius != null && !radius.isEmpty()) {
            request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static boolean checkRequest(HttpServletRequest request){
        float x = Float.parseFloat(request.getParameter("x_value"));
        float y = Float.parseFloat(request.getParameter("y_value_input"));
        double r = Double.parseDouble(request.getParameter("radius"));

        return x >= -4 && x <=4
                && y >=-5 && y <=5
                && r >= 1 && r <= 3;
    }
}
