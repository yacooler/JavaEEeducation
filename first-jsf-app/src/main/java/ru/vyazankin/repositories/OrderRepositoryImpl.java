package ru.vyazankin.repositories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Order;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Named
@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createNamedQuery("findAllOrders", Order.class).getResultList();
    }

    @Override
    @Transactional
    public Order saveOrUpdate(Order order) {
        if (order.getId() == null){
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
        return order;
    }

    @Override
    @Transactional
    public void delete(Order order) {
        entityManager.remove(order);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteOrderById").setParameter("id", id).executeUpdate();
    }

    @Override
    public Long countAll() {
        return entityManager.createNamedQuery("countAllOrders", Long.class).getSingleResult();
    }

    @Override
    public boolean isEmpty() {
        return countAll() == 0;
    }

    @Override
    public List<Order> findAllByUserId(Long id) {
        return entityManager.createNamedQuery("findAllOrdersByUserId", Order.class).setParameter("user_id", id).getResultList();
    }
}
