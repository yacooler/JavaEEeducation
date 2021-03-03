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

    private List<Product> productList;
    private List<Category> categoryList;
    private Long categoryFilterId = 0L;

    public String createProduct(){
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void refreshData(){
        if (categoryFilterId == 0) {
            productList = productRepository.findAll();
        } else {
            productList = productRepository.findAllByCategoryId(categoryFilterId);
        }

        categoryList = categoryRepository.findAll();
        categoryList.add(new Category("Все категории"));

    }

    public List<Product> getAllProducts(){
        return productList;
    }

    public String editProduct(Product product){
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product){
        productRepository.deleteById(product.getId());
        //return "/product.xhtml";
    }

    public String saveProduct(){
        productRepository.saveOrUpdate(this.product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getCategoryList(){
        return categoryList;
    }

    public void setFilter(Long categoryFilterId){
        if (categoryFilterId==null) categoryFilterId = 0L;
        this.categoryFilterId = categoryFilterId;
        refreshData();
    }

}
