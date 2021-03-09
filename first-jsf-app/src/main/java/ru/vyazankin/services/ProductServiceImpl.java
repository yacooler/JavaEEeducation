package ru.vyazankin.services;


import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.common.services.ProductServiceRemote;
import ru.vyazankin.mappers.ProductMapper;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;



@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private ProductMapper productMapper;

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }


    @TransactionAttribute
    @Override
    public ProductDto saveOrUpdate(ProductDto t) {
        Product product = productMapper.toEntity(t);
        productRepository.saveOrUpdate(product);
        return productMapper.toDto(product);
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @TransactionAttribute
    @Override
    public void delete(ProductDto productDto) {
        productRepository.deleteById(productDto.getId());
    }

    @Override
    public boolean isEmpty() {
        return productRepository.isEmpty();
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public List<ProductDto> findAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(productMapper::toDto).collect(Collectors.toList());
    }
}
