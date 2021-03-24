package ru.vyazankin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.persists.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepositoryImpl implements RoleRepository{

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        return entityManager.createNamedQuery("findRoleByName", Role.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createNamedQuery("findAllRoles", Role.class).getResultList();
    }

    @Override
    public Role saveOrUpdate(Role role) {
        if (role.getId() == null) {
            entityManager.persist(role);
        } else {
            entityManager.merge(role);
        }
        return role;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteRoleById").setParameter("id", id).executeUpdate();
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Override
    public boolean isEmpty() {
        return (countAll() == 0);
    }

    @Override
    public Long countAll() {
        return entityManager.createNamedQuery("countAllRoles", Long.class).getSingleResult();
    }

    @Override
    public Role getReference(Long id) {
        return entityManager.getReference(Role.class, id);
    }
}
