package ru.vyazankin.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vyazankin.persists.Order;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private String userName;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.userName = order.getUser().getName();
    }
}
