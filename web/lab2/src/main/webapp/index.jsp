<%@ page import="data.Result" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная 2</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #3498db;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 100;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .header p {
            margin: 5px 0;
            font-size: 18px;
        }

        .main-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            margin-top: 20px;
        }
        .graph-container {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 500px;
            height: 500px;
            margin: 0 auto;

        }

        .content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin: 20px 0;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            padding: 5px;
            width: 50px;
            margin-top: 5px;
        }

        .x-button, .radius-button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            background-color: #3498db;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }

        .x-button.selected, .radius-button.selected {
            background-color: #2ecc71;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        td {
            text-align: center;
        }

        img {
            display: block;
            width: 100%;
            height: auto;
            padding: 0;
            margin: 0;
            border: none;
        }


        .dot {
            position: absolute;
            width: 10px;
            height: 10px;
            border-radius: 50%;
        }
    </style>

    <script type="text/javascript">
        function handleClick(event) {
            var radius = parseFloat(document.getElementById("radius_hidden").value);
            if (isNaN(radius) || radius <= 0) {
                alert("Пожалуйста, выберите радиус перед кликом по плоскости.");
                return;
            }

            var img = event.target; // Получаем элемент изображения
            var rect = img.getBoundingClientRect(); // Получаем размеры и положение изображения на странице

            // Получаем точные координаты клика относительно изображения
            var clickX = event.clientX - rect.left;
            var clickY = event.clientY - rect.top;

            // Преобразуем координаты клика в координаты графика
            var x = ((clickX / rect.width) * 2 - 1) * radius;
            var y = ((1 - (clickY / rect.height) * 2)) * radius;

            console.log("Click at X: " + clickX + ", Y: " + clickY);
            console.log("Transformed X: " + x.toFixed(2) + ", Transformed Y: " + y.toFixed(2));

            // Устанавливаем вычисленные значения в форму
            document.getElementById('x_value').value = x.toFixed(2);
            document.getElementById('y_value').value = y.toFixed(2);

            // Устанавливаем флаг, что был клик по изображению
            document.getElementById('image_click').value = "true";

            // Отправляем форму для обработки на сервере
            document.getElementById('coords_form').submit();
        }


        function validateForm() {
            var x = document.getElementById("x_value").value;
            var y = document.getElementById("y_value_input").value || document.getElementById("y_value").value;
            var radius = document.getElementById("radius_hidden").value;

            if (isNaN(y) || y < -5 || y > 5) {
                alert("Y должно быть числом от -5 до 5.");
                return false;
            }
            if (!y) {
                alert("Пожалуйста, выберите Y.");
                return false;
            }

            if (!x) {
                alert("Пожалуйста, выберите X.");
                return false;
            }

            if (!radius) {
                alert("Пожалуйста, выберите радиус.");
                return false;
            }

            document.getElementById('y_value').value = y;

            return true;
        }

        function setR(value) {
            document.getElementById("radius_hidden").value = value;
            document.querySelectorAll('.radius-button').forEach(btn => btn.classList.remove('selected'));
            document.getElementById("r" + value).classList.add('selected');
        }

        function setX(value) {
            document.getElementById("x_value").value = value;
            document.querySelectorAll('.x-button').forEach(btn => btn.classList.remove('selected'));
            document.getElementById("x" + value).classList.add('selected');
        }
    </script>
</head>
<body>

<header>
    <div class="header">
        <p>ФИО: Рощин Константин Эдуардович</p>
        <p>Группа: Р3222</p>
        <p>Номер варианта: 12200</p>
    </div>
</header>

<div class="main-container">
    <div class="content">
        <h2>Проверка попадания точки в область</h2>

        <form id="coords_form" name="coords_form" action="ControllerServlet" method="GET" accept-charset="UTF-8" onsubmit="return validateForm();">
            <input type="hidden" id="image_click" name="image_click" value="false">
            <label>Изменение X:</label>
            <div>
                <button type="button" class="x-button" id="x-4" onclick="setX(-4)">-4</button>
                <button type="button" class="x-button" id="x-3" onclick="setX(-3)">-3</button>
                <button type="button" class="x-button" id="x-2" onclick="setX(-2)">-2</button>
                <button type="button" class="x-button" id="x-1" onclick="setX(-1)">-1</button>
                <button type="button" class="x-button" id="x0" onclick="setX(0)">0</button>
                <button type="button" class="x-button" id="x1" onclick="setX(1)">1</button>
                <button type="button" class="x-button" id="x2" onclick="setX(2)">2</button>
                <button type="button" class="x-button" id="x3" onclick="setX(3)">3</button>
                <button type="button" class="x-button" id="x4" onclick="setX(4)">4</button>
            </div>
            <input type="hidden" id="x_value" name="x_value">
            <input type="hidden" id="y_value" name="y_value">

            <br>

            <label for="y_value_input">Изменение Y (от -5 до 5):</label>
            <input type="text" id="y_value_input" name="y_value_input" >
            <br>

            <label>Изменение R:</label>
            <div>
                <button type="button" class="radius-button" id="r1" onclick="setR(1)">1</button>
                <button type="button" class="radius-button" id="r1.5" onclick="setR(1.5)">1.5</button>
                <button type="button" class="radius-button" id="r2" onclick="setR(2)">2</button>
                <button type="button" class="radius-button" id="r2.5" onclick="setR(2.5)">2.5</button>
                <button type="button" class="radius-button" id="r3" onclick="setR(3)">3</button>
            </div>
            <input type="hidden" id="radius_hidden" name="radius">
            <br>

            <input type="submit" value="Проверить точку">
        </form>

        <h3>График области</h3>
        <p>Нажмите на изображение, чтобы выбрать координаты X и Y:</p>
        <div class="graph-container">
            <img src="image2.jpg" alt="Координатная плоскость" style="width:500px;height:500px;" onclick="handleClick(event);">
            <%
                // Отображение всех точек
                List<Result> points = (List<Result>) session.getAttribute("points");
                if (points != null) {
                    double graphWidth = 500;  // Фактическая ширина графика
                    double graphHeight = 500; // Фактическая высота графика
                    double offsetX = graphWidth / 2;  // Центр по X
                    double offsetY = graphHeight / 2; // Центр по Y

                    for (Result point : points) {
                        // Преобразуем координаты точки для изображения (500px ширина и высота картинки)
                        double px = (point.getX() / point.getRadius()) * (graphWidth / 2) + offsetX;
                        double py = offsetY - (point.getY() / point.getRadius()) * (graphHeight / 2);

                        // Проверка на границы: чтобы точки не выходили за пределы изображения
                        if (px < 0) px = 0;
                        if (px > graphWidth) px = graphWidth;
                        if (py < 0) py = 0;
                        if (py > graphHeight) py = graphHeight;

                        String color = point.isInside() ? "green" : "red";
            %>
            <div class="dot" style="left:<%= px %>px; top:<%= py %>px; background-color:<%= color %>;"></div>
            <%
                    }
                }
            %>
        </div>



        <h3>Результаты</h3>
        <table>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>Радиус</th>
                <th>Результат</th>
            </tr>
            <%
                List<Result> results = (List<Result>) session.getAttribute("results");
                if (results != null) {
                    for (Result result : results) {
            %>
            <tr>
                <td><%= result.getX() %></td>
                <td><%= result.getY() %></td>
                <td><%= result.getRadius() %></td>
                <td><%= result.isInside() ? "Попадание" : "Мимо" %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">Нет данных.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
