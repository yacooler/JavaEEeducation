package ru.vyazankin.repositories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Named
@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createNamedQuery("findAllCategories", Category.class).getResultList();
    }

    @Override
    @Transactional
    public Category saveOrUpdate(Category category) {
        if (category.getId() == null){
            entityManager.persist(category);
        } else {
            entityManager.merge(category);
        }
        return category;
    }

    @Override
    @Transactional
    public void delete(Category category) {
        entityManager.remove(category);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
    }

    @Override
    public Long countAll() {
        return entityManager.createNamedQuery("countAllCategories", Long.class).getSingleResult();
    }

    @Override
    public boolean isEmpty() {
        return countAll() == 0;
    }
}
