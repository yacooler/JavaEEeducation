package ru.vyazankin.servlets;

import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.ProductRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;
    //private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class); //Пример логгера!

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("ProductRepository");
        if (productRepository == null) {
            throw new ServletException("Не удалось получить инстанс ProductRepository из контекста!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null || req.getPathInfo() == "/") {
            //Список продуктов складываем в атрибуты
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);

            //Редактирование
        } else if (req.getPathInfo().equalsIgnoreCase("/edit")) {
            Long productId;
            try {
                productId = Long.parseLong(req.getParameter("id"));
                if (productId == null) {
                    throw new ServletException("Пустой идентификатор продукта в параметре id");
                }
            } catch (NumberFormatException nfe) {
                throw new ServletException("Не удалось получить параметр id", nfe);
            }
            Product product = productRepository.findById(productId);
            if (product == null){
                resp.sendError(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);

            //Добавление
        } else if (req.getPathInfo().equalsIgnoreCase("/add")){
            Product product = new Product();
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);

            //Удаление
        } else if (req.getPathInfo().equalsIgnoreCase("/delete")){
            Long productId;
            try {
                productId = Long.parseLong(req.getParameter("id"));
                if (productId == null) {
                    throw new ServletException("Пустой идентификатор продукта в параметре id");
                }
            } catch (NumberFormatException nfe) {
                throw new ServletException("Не удалось получить параметр id", nfe);
            }

            productRepository.deleteById(productId);
            //Вернулись на продукты
            resp.sendRedirect(getServletContext().getContextPath() + "/product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name") == null){
            //Невалидный Post
            resp.sendError(400, "В теле запроса product [post] отсутствует аргумент name");
            return;
        }
        Product product;
        try {
            String productIdParam = req.getParameter("id");
            Long productId = productIdParam == null || productIdParam.isBlank() ? null : Long.parseLong(req.getParameter("id"));
            product = new Product(
                productId,
                req.getParameter("name"),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("price")));

        } catch (NumberFormatException nfe){
            throw new ServletException("Не удалось получить параметр id", nfe);
        }

        //NPE быть не должно, т.к. выше блок try
        productRepository.saveOrUpdate(product);

        //Вернулись на продукты
        resp.sendRedirect(getServletContext().getContextPath() + "/product");

    }
}
