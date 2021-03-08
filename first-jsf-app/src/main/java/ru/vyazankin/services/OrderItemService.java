package ru.vyazankin.services;

import ru.vyazankin.dto.OrderItemDto;
import ru.vyazankin.persists.OrderItem;

import javax.ejb.Local;

@Local
public interface OrderItemService extends BaseService<OrderItem, OrderItemDto> {
}
