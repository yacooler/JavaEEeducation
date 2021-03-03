package ru.vyazankin.repositories;
import java.util.List;

public interface BaseRepository<T> {
    T findById(Long id);
    default List<T> findAll(){throw new UnsupportedOperationException("Операция чтения всех элементов не определена для данной сущности!");}
    T saveOrUpdate(T t);
    void deleteById(Long id);
    void delete(T t);
    boolean isEmpty();
    Long countAll();
}
