package ru.vyazankin.services;

import ru.vyazankin.common.dto.OrderItemDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
    void addProduct(Long productId);
    void removeProduct(Long productId);
    List<OrderItemDto> getCartItems();
}
