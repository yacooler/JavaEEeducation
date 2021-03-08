package ru.vyazankin.services;


import ru.vyazankin.dto.ProductDto;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.CategoryRepository;
import ru.vyazankin.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class ProductServiceImpl implements ProductService{

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto findById(Long id) {
        return new ProductDto(productRepository.findById(id));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }


    @TransactionAttribute
    @Override
    public ProductDto saveOrUpdate(ProductDto t) {

        Product product = new Product(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getPrice(),
                categoryRepository.getReference(t.getCategoryId()));

        productRepository.saveOrUpdate(product);

        return new ProductDto(product);
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
        return productRepository.findAllByCategoryId(categoryId).stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
