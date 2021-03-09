package ru.vyazankin.repositories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;


    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAllProducts", Product.class).getResultList();

    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
        return product;
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(product);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteProductById")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Long countAll(){
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    @Override
    public boolean isEmpty(){
        return countAll() == 0;
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return entityManager.createNamedQuery("findAllProductsByCategoryId", Product.class)
                .setParameter("category_id", categoryId)
                .getResultList();
    }

    @Override
    public Product getReference(Long id) {
        return entityManager.getReference(Product.class, id);
    }
}
