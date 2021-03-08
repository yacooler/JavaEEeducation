package ru.vyazankin.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.vyazankin.dto.CategoryDto;
import ru.vyazankin.dto.ProductDto;
import ru.vyazankin.persists.Category;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.CategoryRepository;
import ru.vyazankin.repositories.ProductRepository;
import ru.vyazankin.services.CategoryService;
import ru.vyazankin.services.ProductService;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    static final long serialVersionUID = -5455871471998158070L;

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    @Getter
    @Setter
    private ProductDto productDto;


    private List<ProductDto> productList;
    private List<CategoryDto> categoryList;

    private Long categoryFilterId = 0L;

    public String createProduct(){
        productDto = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void refreshData(){
        if (categoryFilterId == 0) {
            productList = productService.findAll();
        } else {
            productList = productService.findAllByCategoryId(categoryFilterId);
        }
        categoryList = categoryService.findAll();
    }

    public List<ProductDto> getAllProducts(){
        return productList;
    }

    public String editProduct(ProductDto productDto){
        this.productDto = productDto;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto productDto){
        productService.deleteById(productDto.getId());
        //return "/product.xhtml";
    }

    public String saveProduct(){
        //Установка или смена категории продукта
        productService.saveOrUpdate(this.productDto);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getCategoryList(){
        return categoryList;
    }

    public List<CategoryDto> getFilteredCategoryList(){
        List<CategoryDto> result = new ArrayList<>(categoryList);
        result.add(new CategoryDto(new Category("Все")));
        return result;
    }

    public void setFilter(Long categoryFilterId){
        if (categoryFilterId==null) categoryFilterId = 0L;
        this.categoryFilterId = categoryFilterId;
        refreshData();
    }

}
