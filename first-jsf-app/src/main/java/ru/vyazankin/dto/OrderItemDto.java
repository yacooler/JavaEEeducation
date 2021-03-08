package ru.vyazankin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vyazankin.persists.OrderItem;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Long count;
    private Long price;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.orderId = orderItem.getOrder().getId();
        this.productId = orderItem.getProduct().getId();
        this.productName = orderItem.getProduct().getName();
        this.count = orderItem.getCount();
        this.price = orderItem.getPrice();
    }

    public OrderItemDto(ProductDto productDto, Long count){
        this.id = null;
        this.orderId = null;
        this.productId = productDto.getId();
        this.productName = productDto.getName();
        this.count = count;
        this.price = productDto.getPrice() * count;
    }
}
