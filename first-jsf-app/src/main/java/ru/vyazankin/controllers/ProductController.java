package ru.vyazankin.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.vyazankin.persists.Category;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.CategoryRepository;
import ru.vyazankin.repositories.ProductRepository;


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

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Getter
    @Setter
    private Product product;

    @Getter
    @Setter
    private Long currentProductCategoryId;

    private List<Product> productList;
    private List<Category> categoryList;

    private Long categoryFilterId = 0L;

    public String createProduct(){
        product = new Product();
        currentProductCategoryId = null;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void refreshData(){
        if (categoryFilterId == 0) {
            productList = productRepository.findAll();
        } else {
            productList = productRepository.findAllByCategoryId(categoryFilterId);
        }

        categoryList = categoryRepository.findAll();

    }

    public List<Product> getAllProducts(){
        return productList;
    }

    public String editProduct(Product product){
        this.product = product;
        this.currentProductCategoryId = product.getCategory().getId();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product){
        productRepository.deleteById(product.getId());
        //return "/product.xhtml";
    }

    public String saveProduct(){
        //Установка или смена категории продукта
        if (product.getCategory() == null
                || !product.getCategory().getId().equals(currentProductCategoryId)){
            product.setCategory(categoryRepository.findById(currentProductCategoryId));
        }

        productRepository.saveOrUpdate(this.product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getCategoryList(){
        return categoryList;
    }

    public List<Category> getFilteredCategoryList(){
        List<Category> result = new ArrayList<>(categoryList);
        result.add(new Category("Все"));
        return result;
    }

    public void setFilter(Long categoryFilterId){
        if (categoryFilterId==null) categoryFilterId = 0L;
        this.categoryFilterId = categoryFilterId;
        refreshData();
    }

}
