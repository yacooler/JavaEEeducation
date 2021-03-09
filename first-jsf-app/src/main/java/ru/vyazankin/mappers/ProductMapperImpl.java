package ru.vyazankin.mappers;

import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.CategoryRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductMapperImpl implements ProductMapper{

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public Product toEntity(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDto.setCategoryId(product.getCategory().getId());
            productDto.setCategoryName(product.getCategory().getName());
        } else {
            productDto.setCategoryId(null);
            productDto.setCategoryName(null);
        }
        return productDto;
    }
}
