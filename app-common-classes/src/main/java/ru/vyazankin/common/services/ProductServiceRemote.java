package ru.vyazankin.common.services;

import ru.vyazankin.common.dto.ProductDto;


import java.util.List;



public interface ProductServiceRemote {
    List<ProductDto> findAll();
    List<ProductDto> findAllByCategoryId(Long categoryId);
    ProductDto findById(Long id);
}