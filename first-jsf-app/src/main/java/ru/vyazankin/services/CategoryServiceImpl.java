package ru.vyazankin.services;

import ru.vyazankin.common.dto.CategoryDto;
import ru.vyazankin.persists.Category;
import ru.vyazankin.repositories.CategoryRepository;
import ru.vyazankin.rest.CategoryServiceRest;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto findById(Long id) {
        return new CategoryDto(categoryRepository.findById(id));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    @TransactionAttribute
    @Override
    public CategoryDto saveOrUpdate(CategoryDto t) {
        Category category = new Category(t.getId(), t.getName());
        return new CategoryDto(categoryRepository.saveOrUpdate(category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @TransactionAttribute
    @Override
    public void delete(CategoryDto categoryDto) {
        categoryRepository.deleteById(categoryDto.getId());
    }

    @Override
    public boolean isEmpty() {
        return categoryRepository.isEmpty();
    }

    @Override
    public Long countAll() {
        return categoryRepository.countAll();
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        return saveOrUpdate(categoryDto);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return saveOrUpdate(categoryDto);
    }
}
