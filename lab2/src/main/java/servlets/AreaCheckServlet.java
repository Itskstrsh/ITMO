package servlets;

import data.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AreaCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float x = Float.parseFloat(request.getParameter("x_value"));
        float y = Float.parseFloat(request.getParameter("y_value_input"));
        int r = Integer.parseInt(request.getParameter("radius"));

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
    }


    private boolean checkIfInside(float x, float y, int r) {
        if (x >= -r && x <= 0 && y >= -r && y <= 0) {
            return true;
        }
        if (x >= 0 && y >= 0 && y <= (-2 * x + r) / 2) {
            return true;
        }
        if (x >= 0 && y <= 0 && (x * x + y * y <= (float) (r * r) / 4)) {
            return true;
        }
        return false;

    }
}
