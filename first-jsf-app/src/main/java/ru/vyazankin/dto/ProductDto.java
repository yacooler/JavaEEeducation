package ru.vyazankin.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vyazankin.persists.Product;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long categoryId;
    private String categoryName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
        } else {
            this.categoryId = null;
            this.categoryName = null;
        }
    }




}
