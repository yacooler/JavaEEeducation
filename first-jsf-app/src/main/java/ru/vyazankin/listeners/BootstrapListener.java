package ru.vyazankin.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Category;
import ru.vyazankin.persists.Product;
import ru.vyazankin.persists.Role;
import ru.vyazankin.persists.User;
import ru.vyazankin.repositories.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private UserRepository userRepository;

    @Inject RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(BootstrapListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Check Category repository");
        if (categoryRepository.isEmpty()) {
            categoryRepository.saveOrUpdate(new Category("Память"));
            categoryRepository.saveOrUpdate(new Category("Процессоры"));
            categoryRepository.saveOrUpdate(new Category("Платы"));
            categoryRepository.saveOrUpdate(new Category("Прочее"));
        }

        logger.info("Check Product repository");
        if (productRepository.isEmpty()) {
            logger.info("Product repository is empty! Adding rows");
            productRepository.saveOrUpdate(new Product("RAM 8 gb", "Оперативная память 8ГБ", 4900L, categoryRepository.findById(1L)));

            productRepository.saveOrUpdate(new Product("CPU Intel Xeon", "Серверный процессор Xeon", 27000L, categoryRepository.findById(2L)));
            productRepository.saveOrUpdate(new Product("CPU AMD Ryzen", "Десктопный процессор Ryzen", 13000L, categoryRepository.findById(2L)));

            productRepository.saveOrUpdate(new Product("Motherboard Intel", "Материнская плата для Intel", 20000L, categoryRepository.findById(3L)));
            productRepository.saveOrUpdate(new Product("Motherboard AMD", "Материнская плата для Ryzen", 8900L, categoryRepository.findById(3L)));
        }

        logger.info("Check Role repository");
        if (roleRepository.isEmpty()){
            logger.info("Role repository is empty! Adding rows");
            roleRepository.saveOrUpdate(new Role(null, "ADMIN"));
            roleRepository.saveOrUpdate(new Role(null, "USER"));
        }

        logger.info("Check User repository");
        if (userRepository.isEmpty()) {
            logger.info("User repository is empty! Adding rows");

            User adminUser = new User(null, "bob", "100500");
            adminUser.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));

            //logger.info(adminUser.toString());

            User userUser = new User(null, "bill", "100500");
            userUser.setRoles(Arrays.asList(roleRepository.findByName("USER")));



            userRepository.saveOrUpdate(adminUser);
            userRepository.saveOrUpdate(userUser);

        }

        //logger.info("Check Order repository");

        //logger.info("Check CartItem repository");


    }
}

