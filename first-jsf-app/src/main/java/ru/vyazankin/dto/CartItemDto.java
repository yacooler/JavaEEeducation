package ru.vyazankin.dto;

import lombok.*;
import ru.vyazankin.persists.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Product product;
    private Long count;
    private Long price;
}
