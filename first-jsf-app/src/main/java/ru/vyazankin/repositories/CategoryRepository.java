package ru.vyazankin.repositories;

import ru.vyazankin.persists.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(Long id);
    List<Category> findAll();
    Category saveOrUpdate(Category category);
    void deleteById(Long id);
}