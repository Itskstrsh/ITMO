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
            // Получаем значения из запроса
            String xValueStr = request.getParameter("x_value");
            String yValueStr = request.getParameter("y_value");
            String radiusStr = request.getParameter("radius");

            // Преобразуем строки в числа
            float x = Float.parseFloat(xValueStr);
            float y = Float.parseFloat(yValueStr);
            double r = Double.parseDouble(radiusStr);

            // Проверяем попадание в область
            boolean isInside = checkIfInside(x, y, r);

            // Создаем новый результат
            Result result = new Result(x, y, r, isInside);

            // Получаем сессию и списки результатов
            HttpSession session = request.getSession();
            List<Result> results = (List<Result>) session.getAttribute("results");
            List<Result> points = (List<Result>) session.getAttribute("points");

            if (results == null) {
                results = new ArrayList<>();
            }

            if (points == null) {
                points = new ArrayList<>();
            }

            // Добавляем результат в общий список
            results.add(result);
            points.add(result); // Всегда добавляем точку, независимо от способа ввода

            // Сохраняем списки в сессии
            session.setAttribute("results", results);
            session.setAttribute("points", points);

            // Передаем текущий результат на страницу результата
            request.setAttribute("currentResult", result);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Обрабатываем ошибку преобразования
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

        if (x >= 0 && y <= 0 && (x * x + y * y <= (r * r) / 2)) {
            return true;
        }
        return false;
    }

}


