package ru.vyazankin.persists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Long price;

    public Product(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
