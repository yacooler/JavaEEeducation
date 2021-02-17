package ru.vyazankin.repositories;

import ru.vyazankin.persists.Product;
import ru.vyazankin.persists.User;

import java.util.List;


public interface UserRepository {
    User findById(Long id);
    List<User> findAll();
    User saveOrUpdate(User user);
    void deleteById(Long id);
}
