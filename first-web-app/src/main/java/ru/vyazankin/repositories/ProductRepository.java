package ru.vyazankin.repositories;

import ru.vyazankin.persists.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    void deleteById(Long id);
}
