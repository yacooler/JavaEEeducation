package ru.vyazankin.repositories;

import ru.vyazankin.persists.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    List<Order> findAllByUserId(Long id);
}
