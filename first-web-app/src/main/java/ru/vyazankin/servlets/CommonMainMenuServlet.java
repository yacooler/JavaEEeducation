package ru.vyazankin.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/common-main-menu")
public class CommonMainMenuServlet extends HttpServlet {

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println(getAHref(req.getContextPath(), "/main", "Главная страница", req.getServletPath()));
        resp.getWriter().println(getAHref(req.getContextPath(), "/catalog", "Каталог", req.getServletPath()));
        resp.getWriter().println(getAHref(req.getContextPath(), "/product", "Продукт", req.getServletPath()));
        resp.getWriter().println(getAHref(req.getContextPath(), "/cart", "Корзина", req.getServletPath()));
        resp.getWriter().println(getAHref(req.getContextPath(), "/order", "Заказ", req.getServletPath()));

        resp.getWriter().println("<h1>" + req.getAttribute("pageHeader") + "</h1>");
    }

    private String getAHref(String contextPath, String hrefAddress, String hrefCaption, String servletPath){
        stringBuilder.setLength(0);
        String aHref = stringBuilder
                .append("<a href = \"").append(contextPath).append(hrefAddress).append("\">")
                .append(hrefCaption).append("</a>")
            .toString();

        if (hrefAddress.equalsIgnoreCase(servletPath)){
            aHref = "<b>" + aHref + "</b>";
        }
        return aHref;
    }

}
