package ru.vyazankin.repositories;


import ru.vyazankin.persists.Product;
import ru.vyazankin.persists.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {

    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void init(){
        this.saveOrUpdate(new Product("RAM 8 gb", "Оперативная память 8ГБ", 4900L));
        this.saveOrUpdate(new Product("CPU Intel Xeon", "Серверный процессор Xeon", 27000L));
        this.saveOrUpdate(new Product("CPU AMD Ryzen", "Десктопный процессор Ryzen", 13000L));
        this.saveOrUpdate(new Product("Motherboard Intel", "Материнская плата для Intel", 20000L));
        this.saveOrUpdate(new Product("Motherboard AMD", "Материнская плата для Ryzen", 8900L));
    }

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
