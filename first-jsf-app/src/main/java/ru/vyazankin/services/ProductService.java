package ru.vyazankin.services;

import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.persists.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService extends BaseService<Product, ProductDto>{
    List<ProductDto> findAllByCategoryId(Long categoryId);
}
