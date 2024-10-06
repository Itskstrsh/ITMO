package servlets;

import data.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String xValueStr = request.getParameter("x_value");
            String yValueStr = request.getParameter("y_value");
            String radiusStr = request.getParameter("radius");

            // Преобразуем значения
            float x = Float.parseFloat(xValueStr);
            float y = Float.parseFloat(yValueStr);
            double r = Double.parseDouble(radiusStr);

            boolean isInside = checkIfInside(x, y, r);

            Result result = new Result(x, y, r, isInside);

            HttpSession session = request.getSession();
            List<Result> results = (List<Result>) session.getAttribute("results");

            if (results == null) {
                results = new ArrayList<>();
            }

            results.add(result);
            session.setAttribute("results", results);

            request.setAttribute("currentResult", result);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.getWriter().println("Ошибка: " + e.getMessage());
        }
    }

    private boolean checkIfInside(float x, float y, double r) {
        if (x >= -r && x <= 0 && y >= -r && y <= 0) {
            return true;
        }
        if (x >= 0 && y >= 0 && y <= (-2 * x + r) / 2) {
            return true;
        }
        return x >= 0 && y <= 0 && (x * x + y * y <= (float) (r * r) / 4);
    }
}
