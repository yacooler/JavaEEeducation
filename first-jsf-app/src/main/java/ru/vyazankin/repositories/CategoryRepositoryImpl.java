package ru.vyazankin.repositories;


import ru.vyazankin.persists.Category;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();

    @Override
    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    @Override
    public List<Category> findAll() {
        return List.copyOf(categoryMap.values());
    }

    @Override
    public Category saveOrUpdate(Category category) {
        if (category.getId() == null) category.setId(identity.incrementAndGet());
        categoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteById(Long id) {
        categoryMap.remove(id);
    }

}
