package ru.vyazankin.services;

import ru.vyazankin.common.dto.OrderItemDto;
import ru.vyazankin.persists.OrderItem;

import javax.ejb.Local;

@Local
public interface OrderItemService extends BaseService<OrderItem, OrderItemDto> {
}
