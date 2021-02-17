package ru.vyazankin.listeners;

import ru.vyazankin.persists.Product;
import ru.vyazankin.persists.User;
import ru.vyazankin.repositories.ProductRepository;
import ru.vyazankin.repositories.ProductRepositoryImpl;
import ru.vyazankin.repositories.UserRepository;
import ru.vyazankin.repositories.UserRepositoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepositoryImpl();
        productRepository.saveOrUpdate(new Product("RAM 8 gb", "Оперативная память 8ГБ", 4900));
        productRepository.saveOrUpdate(new Product("CPU Intel Xeon", "Серверный процессор Xeon", 27000));
        productRepository.saveOrUpdate(new Product("CPU AMD Ryzen", "Десктопный процессор Ryzen", 13000));
        productRepository.saveOrUpdate(new Product("Motherboard Intel", "Материнская плата для Intel", 20000));
        productRepository.saveOrUpdate(new Product("Motherboard AMD", "Материнская плата для Ryzen", 8900));

        sce.getServletContext().setAttribute("ProductRepository", productRepository);

        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.saveOrUpdate(new User(null, "bob", "100600"));
        userRepository.saveOrUpdate(new User(null, "bil", "100500"));

        sce.getServletContext().setAttribute("UserRepository", userRepository);
    }
}
