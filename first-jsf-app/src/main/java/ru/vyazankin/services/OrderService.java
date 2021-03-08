package ru.vyazankin.services;

import ru.vyazankin.dto.OrderDto;
import ru.vyazankin.persists.Order;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderService extends BaseService<Order, OrderDto> {
    List<Order> findAllByUserId(Long id);
}
