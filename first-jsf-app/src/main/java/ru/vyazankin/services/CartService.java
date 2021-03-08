package ru.vyazankin.services;

import ru.vyazankin.dto.OrderItemDto;
import ru.vyazankin.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
    void addProduct(Long productId);
    void removeProduct(Long productId);
    List<OrderItemDto> getCartItems();
}
