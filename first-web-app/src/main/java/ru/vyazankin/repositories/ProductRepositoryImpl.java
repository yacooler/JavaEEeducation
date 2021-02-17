package ru.vyazankin.repositories;

import ru.vyazankin.persists.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class ProductRepositoryImpl implements ProductRepository {

    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> findAll(){
        return List.copyOf(productMap.values());
    }

    @Override
    public Product saveOrUpdate(Product product){
        if (product.getId() == null) product.setId(identity.incrementAndGet());
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
