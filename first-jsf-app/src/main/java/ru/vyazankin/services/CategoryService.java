package ru.vyazankin.services;

import ru.vyazankin.common.dto.CategoryDto;
import ru.vyazankin.persists.Category;

import javax.ejb.Local;

@Local
public interface CategoryService extends BaseService<Category, CategoryDto> {
}
