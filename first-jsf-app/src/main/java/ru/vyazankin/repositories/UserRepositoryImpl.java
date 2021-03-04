package ru.vyazankin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;


    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll(){
        return entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    @Override
    @Transactional
    public User saveOrUpdate(User user){
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
    }

    @Override
    public boolean isEmpty() {
        return countAll() == 0;
    }

    @Override
    public Long countAll() {
        return entityManager.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Override
    public User findByName(String name) {
        return entityManager.createNamedQuery("findUserByName", User.class).getSingleResult();
    }
}
