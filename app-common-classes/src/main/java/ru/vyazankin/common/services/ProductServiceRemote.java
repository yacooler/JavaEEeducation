package ru.vyazankin.common.services;

import ru.vyazankin.common.dto.ProductDto;

import javax.ejb.Remote;
import java.util.List;


@Remote
public interface ProductServiceRemote {
    List<ProductDto> findAll();
    List<ProductDto> findAllByCategoryId(Long categoryId);
    ProductDto findById(Long id);
}