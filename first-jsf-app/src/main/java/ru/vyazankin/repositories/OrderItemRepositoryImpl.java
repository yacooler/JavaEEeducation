package ru.vyazankin.repositories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Order;
import ru.vyazankin.persists.OrderItem;
import ru.vyazankin.persists.Product;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Stateless
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;


    @Override
    public OrderItem findById(Long id) {
        return entityManager.find(OrderItem.class, id);
    }

    @Override
    public OrderItem saveOrUpdate(OrderItem orderItem) {
        if (orderItem.getId() == null) {
            entityManager.persist(orderItem);
        } else {
            entityManager.merge(orderItem);
        }
        return orderItem;
    }

    @Override
    public void delete(OrderItem orderItem) {
        entityManager.remove(orderItem);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteOrderItemById").setParameter("id", id).executeUpdate();
    }

    @Override
    public Long countAll(){
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    @Override
    public boolean isEmpty(){
        //return entityManager.createNamedQuery("findAll").setFirstResult(0).setMaxResults(1).getResultList().isEmpty();
        return countAll() == 0;
    }
}
