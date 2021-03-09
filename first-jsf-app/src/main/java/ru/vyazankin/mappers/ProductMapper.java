package ru.vyazankin.mappers;

import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.persists.Product;

import javax.ejb.Local;

@Local
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
}
