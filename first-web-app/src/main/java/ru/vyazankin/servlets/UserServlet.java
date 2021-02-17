package ru.vyazankin.servlets;

import ru.vyazankin.persists.User;
import ru.vyazankin.repositories.UserRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;
    //private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class); //Пример логгера!

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("UserRepository");
        if (userRepository == null) {
            throw new ServletException("Не удалось получить инстанс UserRepository из контекста!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null || req.getPathInfo() == "/") {
            //Список продуктов складываем в атрибуты
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);

            //Редактирование
        } else if (req.getPathInfo().equalsIgnoreCase("/edit")) {
            Long userId;
            try {
                userId = Long.parseLong(req.getParameter("id"));
                if (userId == null) {
                    throw new ServletException("Пустой идентификатор пользователя в параметре id");
                }
            } catch (NumberFormatException nfe) {
                throw new ServletException("Не удалось получить параметр id", nfe);
            }
            User user = userRepository.findById(userId);
            if (user == null){
                resp.sendError(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);

            //Добавление
        } else if (req.getPathInfo().equalsIgnoreCase("/add")){
            User user = new User();
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);

            //Удаление
        } else if (req.getPathInfo().equalsIgnoreCase("/delete")){
            Long userId;
            try {
                userId = Long.parseLong(req.getParameter("id"));
                if (userId == null) {
                    throw new ServletException("Пустой идентификатор пользователя в параметре id");
                }
            } catch (NumberFormatException nfe) {
                throw new ServletException("Не удалось получить параметр id", nfe);
            }

            userRepository.deleteById(userId);
            //Вернулись на продукты
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name") == null){
            //Невалидный Post
            resp.sendError(400, "В теле запроса user [post] отсутствует аргумент name");
            return;
        }
        User user;
        try {
            String userIdParam = req.getParameter("id");
            Long userId = userIdParam == null || userIdParam.isBlank() ? null : Long.parseLong(req.getParameter("id"));
            user = new User(
                    userId,
                    req.getParameter("name"),
                    req.getParameter("password"));

        } catch (NumberFormatException nfe){
            throw new ServletException("Не удалось получить параметр id", nfe);
        }

        //NPE быть не должно, т.к. выше блок try
        userRepository.saveOrUpdate(user);

        //Вернулись на продукты
        resp.sendRedirect(getServletContext().getContextPath() + "/user");

    }
}
