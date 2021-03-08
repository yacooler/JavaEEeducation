package ru.vyazankin.services;

import ru.vyazankin.dto.ProductDto;
import ru.vyazankin.persists.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService extends BaseService<Product, ProductDto>{
    List<ProductDto> findAllByCategoryId(Long categoryId);
}
