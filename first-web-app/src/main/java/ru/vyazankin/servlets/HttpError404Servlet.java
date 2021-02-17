package ru.vyazankin.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/page-not-found")

public class HttpError404Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h2>Ошибка! Запрашиваемая страница не найдена!</h2>");
        resp.getWriter().println("<br><a href=\"#\" onclick=\"history.back();return false;\">Назад</a>");

    }
}
