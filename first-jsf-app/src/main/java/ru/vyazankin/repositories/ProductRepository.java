package ru.vyazankin.repositories;

import ru.vyazankin.persists.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product>{
    List<Product> findAllByCategoryId(Long categoryId);
}
