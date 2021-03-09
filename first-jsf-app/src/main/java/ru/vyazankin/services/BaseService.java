package ru.vyazankin.services;

import java.util.List;

public interface BaseService<B, D> {
    D findById(Long id);
    default List<D> findAll(){throw new UnsupportedOperationException("Операция чтения всех элементов не определена для репозитория");}
    D saveOrUpdate(D t);
    void deleteById(Long id);
    void delete(D d);
    boolean isEmpty();
    Long countAll();
}
